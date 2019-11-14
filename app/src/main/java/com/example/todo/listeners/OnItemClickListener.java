package com.example.todo.listeners;

import com.example.todo.db.entity.TodoCategoryEntity;

public interface OnItemClickListener {
    void onItemClick(TodoCategoryEntity item);
}
