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

    TodoRepository todoRepository;

    @Inject
    public TodoViewModel(@NonNull Application application, TodoRepository todoRepository) {
        super(application);
        this.todoRepository = todoRepository;
    }

    public void insertTodo(TodoEntity todoEntity){
        todoRepository.insert(todoEntity);
    }

    public void updateTodo(TodoEntity todoEntity){
        todoRepository.update(todoEntity);
    }

    public void deleteTodo(TodoEntity todoEntity){
        todoRepository.delete(todoEntity);
    }

    public LiveData<List<TodoEntity>> getTodos(long catId){ return todoRepository.getCategoryTodos(catId);}
}
