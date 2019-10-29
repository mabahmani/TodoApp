package com.example.todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo.db.entity.TodoEntity;
import com.example.todo.repository.TodoRepository;

import java.util.List;

import javax.inject.Inject;

public class TodoViewModel extends AndroidViewModel {

    @Inject
    TodoRepository todoRepository;

    private LiveData<List<TodoEntity>> todos;

    public TodoViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(TodoEntity todoEntity){
        todoRepository.insert(todoEntity);
    }

    public void update(TodoEntity todoEntity){
        todoRepository.update(todoEntity);
    }

    public void delete(TodoEntity todoEntity){
        todoRepository.insert(todoEntity);
    }

    public LiveData<List<TodoEntity>> getTodos(int catId){
        todos = todoRepository.getCategoryTodos(catId);
        return todos;
    }
}
