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

    @Inject
    TodoCategoryRepository todoCategoryRepository;

    private LiveData<List<TodoCategoryEntity>> todoCategories;

    public TodoCategoryViewModel(@NonNull Application application) {
        super(application);
        todoCategories = todoCategoryRepository.getAllTodoCategory();
    }

    public LiveData<List<TodoCategoryEntity>> getAllCategories(){
        return todoCategories;
    }

    public void insertCategory(TodoCategoryEntity todoCategoryEntity){
        todoCategoryRepository.insert(todoCategoryEntity);
    }

    public void updateCategory(TodoCategoryEntity todoCategoryEntity){
        todoCategoryRepository.update(todoCategoryEntity);
    }

    public void deleteCategory(TodoCategoryEntity todoCategoryEntity){
        todoCategoryRepository.delete(todoCategoryEntity);
    }
}
