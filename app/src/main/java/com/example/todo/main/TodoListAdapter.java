package com.example.todo.main;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.db.entity.TodoEntity;
import com.example.todo.util.FaDigitsConverter;
import com.example.todo.viewmodel.TodoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_COMPLETED = 2;
    private Context context;
    private List<TodoEntity> todoModelList;
    private TodoViewModel todoViewModel;

    public TodoListAdapter(Context context, List<TodoEntity> todoModelList, TodoViewModel todoViewModel) {
        this.context = context;
        this.todoModelList = todoModelList;
        this.todoViewModel = todoViewModel;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ViewHolderHeader){

                DateModel dateModel = (DateModel) todoModelList.get(position);
                ViewHolderHeader holderHeader = (ViewHolderHeader) holder;

                PersianDateFormat persianDateFormat = new PersianDateFormat("l , j F");
                PersianDate persianDate = new PersianDate(dateModel.getDueDate());
                holderHeader.date.setText(FaDigitsConverter.convert(persianDateFormat.format(persianDate)));
            }

            else if (holder instanceof ViewHolderItem){

                ViewHolderItem holderItem = (ViewHolderItem) holder;

                holderItem.task.setText(todoModelList.get(position).getTask());
                holderItem.subTask.setText(todoModelList.get(position).getSubTask());

                holderItem.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        todoModelList.get(position).setCompleted(true);
                        todoViewModel.updateTodo(todoModelList.get(position));
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

                if (completedTodoModel.isExpanded()){
                    holderCompleted.expoandIcon.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }

                else {
                    holderCompleted.expoandIcon.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }

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
        @BindView(R.id.expand_icon)
        AppCompatImageView expoandIcon;

        public ViewHolderCompleted(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
