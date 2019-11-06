package com.example.todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.repository.TodoCategoryRepository;

import java.util.List;

import javax.inject.Inject;

public class TodoCategoryViewModel extends AndroidViewModel {

    TodoCategoryRepository todoCategoryRepository;

    private LiveData<List<TodoCategoryEntity>> todoCategories;

    @Inject
    public TodoCategoryViewModel(@NonNull Application application, TodoCategoryRepository todoCategoryRepository) {
        super(application);
        this.todoCategories = todoCategoryRepository.getAllTodoCategory();
        this.todoCategoryRepository = todoCategoryRepository;
    }

    public LiveData<List<TodoCategoryEntity>> getAllCategories(){
        return todoCategories;
    }

    public LiveData<Long> insertCategory(TodoCategoryEntity todoCategoryEntity){
        return todoCategoryRepository.insert(todoCategoryEntity);
    }

    public void updateCategory(TodoCategoryEntity todoCategoryEntity){
        todoCategoryRepository.update(todoCategoryEntity);
    }

    public void deleteCategory(TodoCategoryEntity todoCategoryEntity){
        todoCategoryRepository.delete(todoCategoryEntity);
    }
}
