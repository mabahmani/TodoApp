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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
