package com.example.todo.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.util.SharedConstants;
import com.example.todo.viewmodel.TodoCategoryViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class TodosFragment extends DaggerFragment {

    @BindView(R.id.todo_list)
    RecyclerView todoListRv;
    @BindView(R.id.list_name)
    AppCompatTextView listName;

    @Inject
    TodoCategoryViewModel todoCategoryViewModel;
    @Inject
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todos,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        todoCategoryViewModel.getCategory(sharedPreferences.getLong(SharedConstants.ACTIVE_CATEGORY,-1)).observe(this, new Observer<TodoCategoryEntity>() {
            @Override
            public void onChanged(TodoCategoryEntity todoCategoryEntity) {
                listName.setText(todoCategoryEntity.getCategoryName());
            }
        });

        initList();
    }

    private void initList() {

        List<TodoModel> todoModels = new ArrayList<>();
        List<TodoModel> completedeList = new ArrayList<>();

        Date date = new Date();

        for (int i=0; i<20; i++){
            TodoModel todoModel = new TodoModel();
            todoModel.setTask("Task " + i);
            todoModel.setSubTask("SubTask " + i);
            Calendar calendar = Calendar.getInstance();
            if (i%6==0){
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_YEAR, i);
                date = calendar.getTime();

                DateModel dateModel = new DateModel();
                dateModel.setDueDate(date);
                todoModels.add(dateModel);
            }

            todoModel.setDueDate(date);
            todoModels.add(todoModel);
        }

        CompletedTodoModel completedTodoModel = new CompletedTodoModel();
        for (int i=0; i<10; i++){
            TodoModel todoModel = new TodoModel();
            todoModel.setTask("Completed task " + i);
            completedeList.add(todoModel);
        }
        completedTodoModel.setCompletedList(completedeList);
        todoModels.add(completedTodoModel);

        TodoListAdapter todoListAdapter = new TodoListAdapter(getContext(), todoModels);
        todoListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        todoListRv.setAdapter(todoListAdapter);
    }

}
