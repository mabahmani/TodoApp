package com.example.todo.main;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.todo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationBottomSheetFragment extends BottomSheetDialogFragment {

    @BindView(R.id.account_parent)
    ConstraintLayout accountParent;
    @BindView(R.id.manage_account_parent)
    ConstraintLayout manageAccountParent;
    @BindView(R.id.expand_icon)
    AppCompatImageView expandIcon;

    private boolean exapnd = false;

    public NavigationBottomSheetFragment() {
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

        ViewGroup viewGroup = (ViewGroup) view;
        accountParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(viewGroup);
                }

                manageAccountParent.setVisibility(exapnd ? View.GONE : View.VISIBLE);

                if (exapnd){
                    expandIcon.setImageResource(R.drawable.ic_expand_more_black_24dp);
                    exapnd = false;
                }
                else {
                    expandIcon.setImageResource(R.drawable.ic_expand_less_black_24dp);
                    exapnd = true;
                }
            }


        });
    }
}
