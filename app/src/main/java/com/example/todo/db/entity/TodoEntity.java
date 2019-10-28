package com.example.todo.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        foreignKeys = @ForeignKey(
                entity = TodoCategoryEntity.class,
                parentColumns = "id",
                childColumns = "categoryId",
                onDelete = CASCADE))
public class TodoEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String task;
    private String subTask;
    private Date date;
    private int categoryId;

    public TodoEntity(int id, String task, String subTask, Date date, int categoryId) {
        this.id = id;
        this.task = task;
        this.subTask = subTask;
        this.date = date;
        this.categoryId = categoryId;
    }
}
