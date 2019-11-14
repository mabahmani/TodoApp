package com.example.todo.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(indices = {@Index(value = {"categoryId"})},
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
    private long categoryId;

    public TodoEntity(String task, String subTask, Date date, long categoryId) {
        this.task = task;
        this.subTask = subTask;
        this.date = date;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSubTask() {
        return subTask;
    }

    public void setSubTask(String subTask) {
        this.subTask = subTask;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
