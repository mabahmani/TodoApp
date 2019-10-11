package com.example.todo.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompletedTodoListAdapter extends RecyclerView.Adapter<CompletedTodoListAdapter.ViewHolder> {

    private List<TodoModel> completedList;

    public CompletedTodoListAdapter(List<TodoModel> completedList) {
        this.completedList = completedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo_list_completed,parent,false);
        ButterKnife.bind(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.todoText.setText(completedList.get(position).getTask());
        holder.todoSubTask.setText(completedList.get(position).getSubTask());
    }

    @Override
    public int getItemCount() {
        return completedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.todo_text)
        AppCompatTextView todoText;
        @BindView(R.id.todo_sub_text)
        AppCompatTextView todoSubTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
