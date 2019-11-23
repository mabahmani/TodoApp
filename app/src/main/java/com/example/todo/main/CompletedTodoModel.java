package com.example.todo.main;

import com.example.todo.db.entity.TodoEntity;

import java.util.Date;
import java.util.List;

public class CompletedTodoModel extends TodoEntity {

    private List<TodoEntity> completedList;
    private boolean expanded;

    public CompletedTodoModel(String task, String subTask, Date date, long categoryId) {
        super(task, subTask, date, categoryId);
        this.expanded = false;

    }


    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<TodoEntity> getCompletedList() {
        return completedList;
    }

    public void setCompletedList(List<TodoEntity> completedList) {
        this.completedList = completedList;
    }
}
