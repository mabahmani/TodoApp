package com.example.todo.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todo.AppExecutors;
import com.example.todo.db.dao.TodoCategoryDao;
import com.example.todo.db.entity.TodoCategoryEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TodoCategoryRepository {

    private AppExecutors appExecutors;
    private TodoCategoryDao todoCategoryDao;

    @Inject
    public TodoCategoryRepository(AppExecutors appExecutors, TodoCategoryDao todoCategoryDao) {
        this.appExecutors = appExecutors;
        this.todoCategoryDao = todoCategoryDao;
    }

    public LiveData<Long> insert(TodoCategoryEntity todoCategoryEntity){

        MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();
        appExecutors.diskIO().execute(() -> mutableLiveData.postValue(todoCategoryDao.insert(todoCategoryEntity)));

        return mutableLiveData;
    }

    public void update(TodoCategoryEntity todoCategoryEntity){
        appExecutors.diskIO().execute(() -> todoCategoryDao.update(todoCategoryEntity));
    }

    public void delete(TodoCategoryEntity todoCategoryEntity){
        appExecutors.diskIO().execute(() -> todoCategoryDao.delete(todoCategoryEntity));
    }

    public LiveData<List<TodoCategoryEntity>> getAllTodoCategory(){
        return todoCategoryDao.getAllCategories();
    }

    public LiveData<TodoCategoryEntity> getCategory(long catId){
        return todoCategoryDao.getCategory(catId);
    }
}
