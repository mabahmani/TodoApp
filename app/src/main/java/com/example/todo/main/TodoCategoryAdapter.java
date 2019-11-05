package com.example.todo.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoCategoryAdapter extends RecyclerView.Adapter<TodoCategoryAdapter.ViewHolder> {

    private List<TodoCategoryEntity> todoCategoryList;

    public TodoCategoryAdapter() {
        todoCategoryList = new ArrayList<>();
    }

    public void setTodoCategoryList(List<TodoCategoryEntity> todoCategoryList) {
        this.todoCategoryList = todoCategoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo_category_list,parent,false);
        ButterKnife.bind(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(todoCategoryList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return todoCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.parent)
        ConstraintLayout parent;
        @BindView(R.id.todo_category_name)
        AppCompatTextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
