package com.example.todo.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_COMPLETED = 2;
    private Context context;
    private List<TodoModel> todoModelList;

    public TodoListAdapter(Context context, List<TodoModel> todoModelList) {
        this.context = context;
        this.todoModelList = todoModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_HEADER) {
            View v = inflater.inflate(R.layout.row_todo_date, parent, false);
            ButterKnife.bind(v);
            return  new ViewHolderHeader(v);
        }

        else if (viewType == TYPE_ITEM){
            View v = inflater.inflate(R.layout.row_todo_list_sort_by_date, parent, false);
            ButterKnife.bind(v);
            return new ViewHolderItem(v);
        }

        else {
            View v = inflater.inflate(R.layout.row_todo_completed, parent, false);
            ButterKnife.bind(v);
            return new ViewHolderCompleted(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ViewHolderHeader){

                DateModel dateModel = (DateModel) todoModelList.get(position);
                ViewHolderHeader holderHeader = (ViewHolderHeader) holder;

                Log.d("aminListSize", todoModelList.size() + "");
                if (dateModel.getDueDate() != null)
                    holderHeader.date.setText(dateModel.getDueDate().toString());
            }

            else if (holder instanceof ViewHolderItem){

                ViewHolderItem holderItem = (ViewHolderItem) holder;

                holderItem.task.setText(todoModelList.get(position).getTask());
                holderItem.subTask.setText(todoModelList.get(position).getSubTask());

                holderItem.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    }
                });
            }

            else if (holder instanceof ViewHolderCompleted){
                ViewHolderCompleted holderCompleted = (ViewHolderCompleted) holder;
                CompletedTodoModel completedTodoModel = (CompletedTodoModel) todoModelList.get(position);
                CompletedTodoListAdapter completedTodoListAdapter = new CompletedTodoListAdapter(completedTodoModel.getCompletedList());
                holderCompleted.todoCompletedRv.setLayoutManager(new LinearLayoutManager(context));
                holderCompleted.todoCompletedRv.setAdapter(completedTodoListAdapter);
                holderCompleted.todoCompletedRv.setVisibility(completedTodoModel.isExpanded() ? View.VISIBLE : View.GONE);

                holderCompleted.parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        completedTodoModel.setExpanded(!completedTodoModel.isExpanded());
                        notifyItemChanged(position);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return todoModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (todoModelList.get(position) instanceof DateModel)
            return TYPE_HEADER;
        else if (todoModelList.get(position) instanceof CompletedTodoModel)
            return TYPE_COMPLETED;
        else
            return TYPE_ITEM;
    }

    public class ViewHolderHeader extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        AppCompatTextView date;

        public ViewHolderHeader(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {
        @BindView(R.id.todo_text)
        AppCompatTextView task;
        @BindView(R.id.todo_sub_text)
        AppCompatTextView subTask;
        @BindView(R.id.todo_check_box)
        AppCompatCheckBox checkBox;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class ViewHolderCompleted extends RecyclerView.ViewHolder {
        @BindView(R.id.todo_completed_list)
        RecyclerView todoCompletedRv;
        @BindView(R.id.header_parent)
        LinearLayout parent;
        @BindView(R.id.parent)
        ConstraintLayout mainParent;

        public ViewHolderCompleted(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
