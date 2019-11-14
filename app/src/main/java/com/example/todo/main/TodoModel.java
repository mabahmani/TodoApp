package com.example.todo.main;

import com.example.todo.db.entity.TodoEntity;

import java.util.Date;

public class TodoModel extends TodoEntity {
    private boolean completed;


    public TodoModel(String task, String subTask, Date date, long categoryId) {
        super(task, subTask, date, categoryId);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
