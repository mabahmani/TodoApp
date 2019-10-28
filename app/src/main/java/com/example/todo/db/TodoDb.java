package com.example.todo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todo.db.dao.TodoCategoryDao;
import com.example.todo.db.dao.TodoDao;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.db.entity.TodoEntity;

@Database(
        entities = {TodoEntity.class, TodoCategoryEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class TodoDb extends RoomDatabase {

    abstract TodoDao todoDao();

    abstract TodoCategoryDao todoCategoryDao();
}
