package com.example.todo.main;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.alirezaafkar.sundatepicker.DatePicker;
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;
import com.example.todo.R;
import com.example.todo.db.entity.TodoEntity;
import com.example.todo.util.SharedConstants;
import com.example.todo.viewmodel.TodoViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTodoBottomSheerFragment extends BottomSheetDialogFragment implements DateSetListener{

    @BindView(R.id.add_todo_title)
    AppCompatEditText addTodoTitle;
    @BindView(R.id.add_todo_details)
    AppCompatEditText addTodoDetails;
    @BindView(R.id.add_details)
    AppCompatImageView addDetails;
    @BindView(R.id.date_picker)
    AppCompatImageView datePicker;
    @BindView(R.id.material_text_button)
    MaterialButton saveButton;


    private Date todoDate;

    private TodoViewModel todoViewModel;
    private SharedPreferences sharedPreferences;

    @Inject
    public AddTodoBottomSheerFragment(TodoViewModel todoViewModel, SharedPreferences sharedPreferences) {
        this.todoViewModel = todoViewModel;
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
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_add_todo,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addTodoTitle.setText("");
        addTodoDetails.setText("");
        addTodoTitle.requestFocus();

        todoDate = new Date();

        addDetails.setOnClickListener(view1 -> {
            addTodoDetails.setVisibility(View.VISIBLE);
            addTodoDetails.requestFocus();
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                new DatePicker.Builder().date(calendar)
                        .build(AddTodoBottomSheerFragment.this)
                        .show(getFragmentManager(),"");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(addTodoTitle.getText())){
                    addTodoTitle.setError("نمی تواند خالی باشد.");
                }

                else{
                    TodoEntity todoEntity = new TodoEntity(
                            addTodoTitle.getText().toString(),
                            addTodoDetails.getText() == null ? null:addTodoDetails.getText().toString(),
                            todoDate,
                            sharedPreferences.getLong(SharedConstants.ACTIVE_CATEGORY,-1)
                            );

                    Log.d("aminCatID",todoEntity.getCategoryId() + "");
                    todoViewModel.insertTodo(todoEntity);
                }

                dismiss();
            }
        });
    }

    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
        todoDate = Objects.requireNonNull(calendar).getTime();
    }

    @Override
    public void onResume() {
        super.onResume();
        addTodoTitle.setText("");
        addTodoDetails.setText("");
    }
}
