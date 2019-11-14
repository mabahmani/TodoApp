package com.example.todo.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.db.entity.TodoEntity;
import com.example.todo.util.sharedprefenceslivedata.SharedPreferenceLongLiveData;
import com.example.todo.viewmodel.TodoCategoryViewModel;
import com.example.todo.viewmodel.TodoViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    TodoViewModel todoViewModel;
    @Inject
    SharedPreferenceLongLiveData sharedPreferenceLongLiveData;


    private TodoListAdapter todoListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todos,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        todoListRv.setLayoutManager(new LinearLayoutManager(getContext()));


        sharedPreferenceLongLiveData.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {

                Log.d("aminSHred","changed "+ aLong );

                LiveData<TodoCategoryEntity> activeCategory = todoCategoryViewModel.getCategory(aLong);
                activeCategory.observe(TodosFragment.this, new Observer<TodoCategoryEntity>() {
                    @Override
                    public void onChanged(TodoCategoryEntity todoCategoryEntity) {
                        listName.setText(todoCategoryEntity.getCategoryName());
                        activeCategory.removeObserver(this);
                    }
                });

                todoViewModel.getTodos(aLong).observe(TodosFragment.this, new Observer<List<TodoEntity>>() {
                    @Override
                    public void onChanged(List<TodoEntity> todoEntities) {

                        List<TodoModel> todoModels = new ArrayList<>();

                        if (!todoModels.isEmpty()) {
                            Date initDate = todoEntities.get(0).getDate();
                            DateModel dateModel = new DateModel(todoEntities.get(0).getTask(), todoEntities.get(0).getSubTask(), todoEntities.get(0).getDate(), todoEntities.get(0).getCategoryId());
                            dateModel.setDueDate(initDate);
                            todoModels.add(dateModel);

                            for (TodoEntity t : todoEntities) {

                                if (!isSameDay(initDate, t.getDate())) {
                                    initDate = t.getDate();
                                    dateModel = new DateModel(t.getTask(), t.getSubTask(), t.getDate(), t.getCategoryId());
                                    dateModel.setDueDate(initDate);
                                    todoModels.add(dateModel);
                                }

                                TodoModel todoModel = new TodoModel(t.getTask(), t.getSubTask(), t.getDate(), t.getCategoryId());
                                todoModels.add(todoModel);

                                todoListAdapter = new TodoListAdapter(getContext(), todoModels);
                                todoListRv.setAdapter(todoListAdapter);
                            }
                        }
                    }
                });
            }
        });

    }

    private boolean isSameDay(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return fmt.format(date1).equals(fmt.format(date2));
    }


    private void initList() {

        /*List<TodoModel> todoModels = new ArrayList<>();
        List<TodoModel> completedList = new ArrayList<>();

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

            todoModel.setDate(date);
            todoModels.add(todoModel);
        }

        CompletedTodoModel completedTodoModel = new CompletedTodoModel();
        for (int i=0; i<10; i++){
            TodoModel todoModel = new TodoModel();
            todoModel.setTask("Completed task " + i);
            completedList.add(todoModel);
        }
        completedTodoModel.setCompletedList(completedList);
        todoModels.add(completedTodoModel);*/
    }

}
