package com.example.todo.main;

import com.example.todo.db.entity.TodoEntity;

import java.util.Date;

public class DateModel extends TodoEntity {

    private Date dueDate;

    public DateModel(String task, String subTask, Date date, long categoryId) {
        super(task, subTask, date, categoryId);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
