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


                        todoViewModel.getTodos(aLong).observe(TodosFragment.this, new Observer<List<TodoEntity>>() {
                            @Override
                            public void onChanged(List<TodoEntity> todoEntities) {
                                List<TodoEntity> todoModels = new ArrayList<>();
                                List<TodoEntity> completedTodoModels = new ArrayList<>();
                                CompletedTodoModel completedTodoModel = new CompletedTodoModel("","",new Date(),-1);

                                if (!todoEntities.isEmpty()) {
                                    Date initDate = todoEntities.get(0).getDate();
                                    DateModel dateModel = new DateModel(todoEntities.get(0).getTask(), todoEntities.get(0).getSubTask(), todoEntities.get(0).getDate(), todoEntities.get(0).getCategoryId());
                                    dateModel.setDueDate(initDate);
                                    if (!todoEntities.get(0).isCompleted())
                                        todoModels.add(dateModel);

                                    for (TodoEntity t : todoEntities) {

                                        if (t.isCompleted()){
                                            completedTodoModels.add(t);
                                        }

                                        else {
                                            if (!isSameDay(initDate, t.getDate())) {
                                                initDate = t.getDate();
                                                dateModel = new DateModel(t.getTask(), t.getSubTask(), t.getDate(), t.getCategoryId());
                                                dateModel.setDueDate(initDate);
                                                todoModels.add(dateModel);
                                            }

                                            todoModels.add(t);
                                        }
                                    }

                                    completedTodoModel.setCompletedList(completedTodoModels);
                                    if (!completedTodoModels.isEmpty())
                                        todoModels.add(completedTodoModel);
                                }

                                todoListAdapter = new TodoListAdapter(getContext(), todoModels, todoViewModel);
                                todoListRv.setAdapter(todoListAdapter);
                            }
                        });

                        activeCategory.removeObserver(this);
                    }
                });

            }
        });


    }

    private boolean isSameDay(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return fmt.format(date1).equals(fmt.format(date2));
    }

}
