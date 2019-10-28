package com.example.todo.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TodoCategoryEntity {

    @PrimaryKey(autoGenerate  = true)
    private int id;
    private String categoryName;

    public TodoCategoryEntity(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
