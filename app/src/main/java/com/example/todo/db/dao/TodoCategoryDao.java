package com.example.todo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.db.entity.TodoCategoryEntity;

import java.util.List;

@Dao
public interface TodoCategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert(TodoCategoryEntity todoCategoryEntity);

    @Update
    void update(TodoCategoryEntity todoCategoryEntity);

    @Delete
    void delete(TodoCategoryEntity todoCategoryEntity);

    @Query("SELECT * FROM TodoCategoryEntity")
    LiveData<List<TodoCategoryEntity>> getAllCategories();

}
