package com.example.todo.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodosFragment extends Fragment {

    @BindView(R.id.todo_list)
    RecyclerView todoListRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todos,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
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
