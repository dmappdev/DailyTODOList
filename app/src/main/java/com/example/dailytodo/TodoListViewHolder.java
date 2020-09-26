package com.example.dailytodo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoListViewHolder extends RecyclerView.ViewHolder {

    TextView todoNumber, todoName;

    public TodoListViewHolder(@NonNull View itemView) {
        super(itemView);
        this.todoNumber = itemView.findViewById(R.id.todo_number);
        this.todoName = itemView.findViewById(R.id.todo_name);
    }
}
