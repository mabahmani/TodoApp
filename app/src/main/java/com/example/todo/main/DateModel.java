package com.example.todo.main;

import java.util.Date;

public class DateModel extends TodoModel {

    private Date dueDate;

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
