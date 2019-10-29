package com.example.todo.repository;

import androidx.lifecycle.LiveData;

import com.example.todo.AppExecutors;
import com.example.todo.db.dao.TodoDao;
import com.example.todo.db.entity.TodoEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TodoRepository {

    private AppExecutors appExecutors;
    private TodoDao todoDao;

    @Inject
    public TodoRepository(AppExecutors appExecutors, TodoDao todoDao) {
        this.appExecutors = appExecutors;
        this.todoDao = todoDao;
    }

    public void insert(TodoEntity todoEntity){
        appExecutors.diskIO().execute(() -> todoDao.insert(todoEntity));
    }

    public void update(TodoEntity todoEntity){
        appExecutors.diskIO().execute(() -> todoDao.update(todoEntity));
    }

    public void delete(TodoEntity todoEntity){
        appExecutors.diskIO().execute(() -> todoDao.delete(todoEntity));
    }

    public LiveData<List<TodoEntity>> getCategoryTodos(int catId){
        return todoDao.getCategoryTodos(catId);
    }
}
