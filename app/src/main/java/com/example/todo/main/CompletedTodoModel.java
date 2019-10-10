package com.example.todo.main;

import java.util.List;

public class CompletedTodoModel extends TodoModel {

    private List<TodoModel> completedList;

    public CompletedTodoModel() {
    }

    public List<TodoModel> getCompletedList() {
        return completedList;
    }

    public void setCompletedList(List<TodoModel> completedList) {
        this.completedList = completedList;
    }
}
