package com.example.todo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.db.entity.TodoEntity;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TodoEntity todoEntity);

    @Update
    void update(TodoEntity todoEntity);

    @Delete
    void delete(TodoEntity todoEntity);

    @Query("SELECT * FROM TodoEntity WHERE categoryId=:catId")
    LiveData<List<TodoEntity>> getCategoryTodos(int catId);
}
