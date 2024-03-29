package com.example.todo.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.addcategory.AddCategoryActivity;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.listeners.OnItemClickListener;
import com.example.todo.util.SharedConstants;
import com.example.todo.viewmodel.TodoCategoryViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationBottomSheetFragment extends BottomSheetDialogFragment {

    @BindView(R.id.account_parent)
    ConstraintLayout accountParent;
    @BindView(R.id.manage_account_parent)
    ConstraintLayout manageAccountParent;
    @BindView(R.id.expand_icon)
    AppCompatImageView expandIcon;
    @BindView(R.id.add_new_list)
    AppCompatTextView createNewList;
    @BindView(R.id.todo_category_list)
    RecyclerView todoCategoryRecyclerView;

    private SharedPreferences sharedPreferences;
    private TodoCategoryAdapter todoCategoryAdapter;
    private TodoCategoryViewModel todoCategoryViewModel;
    private boolean expand = false;

    @Inject
    public NavigationBottomSheetFragment(TodoCategoryViewModel todoCategoryViewModel, SharedPreferences sharedPreferences) {
        this.todoCategoryViewModel = todoCategoryViewModel;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_navigation_menu,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        todoCategoryAdapter = new TodoCategoryAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(TodoCategoryEntity item) {
                sharedPreferences.edit().putLong(SharedConstants.ACTIVE_CATEGORY,item.getId()).apply();
                dismiss();
            }
        });


        todoCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        todoCategoryRecyclerView.setAdapter(todoCategoryAdapter);

        todoCategoryViewModel.getAllCategories().observe(this, todoCategoryEntities -> todoCategoryAdapter.setTodoCategoryList(todoCategoryEntities));


        ViewGroup viewGroup = (ViewGroup) view;
        accountParent.setOnClickListener(view1 -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(viewGroup);
            }

            manageAccountParent.setVisibility(expand ? View.GONE : View.VISIBLE);

            if (expand){
                expandIcon.setImageResource(R.drawable.ic_expand_more_black_24dp);
                expand = false;
            }
            else {
                expandIcon.setImageResource(R.drawable.ic_expand_less_black_24dp);
                expand = true;
            }
        });

        createNewList.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
            startActivity(intent);
            dismiss();
        });
    }
}
