package com.example.todo.main;

import java.util.List;

public class CompletedTodoModel extends TodoModel {

    private List<TodoModel> completedList;
    private boolean expanded;

    public CompletedTodoModel() {
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<TodoModel> getCompletedList() {
        return completedList;
    }

    public void setCompletedList(List<TodoModel> completedList) {
        this.completedList = completedList;
    }
}
