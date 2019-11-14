package com.example.todo.main;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.MainApplication;
import com.example.todo.R;
import com.example.todo.db.entity.TodoCategoryEntity;
import com.example.todo.listeners.OnItemClickListener;
import com.example.todo.util.SharedConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoCategoryAdapter extends RecyclerView.Adapter<TodoCategoryAdapter.ViewHolder> {

    private List<TodoCategoryEntity> todoCategoryList;
    private SharedPreferences sharedPreferences;
    private OnItemClickListener itemClickListener;

    public TodoCategoryAdapter(OnItemClickListener onItemClickListener) {
        this.todoCategoryList = new ArrayList<>();
        this.sharedPreferences = MainApplication.getSharedPreferences();
        this.itemClickListener = onItemClickListener;
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
        holder.bind(todoCategoryList.get(position),itemClickListener);

        if (sharedPreferences.getLong(SharedConstants.ACTIVE_CATEGORY,-1) == todoCategoryList.get(position).getId()){
            holder.parent.setBackgroundResource(R.color.colorActiveCategory);
        }

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

        public void bind(TodoCategoryEntity todoCategoryEntity, OnItemClickListener itemClickListener){
            itemView.setOnClickListener(view -> itemClickListener.onItemClick(todoCategoryEntity));
        }
    }
}
