package com.example.dailytodo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoListViewAdapter extends RecyclerView.Adapter<TodoListViewHolder> {

    private ArrayList<String> todoList;
    private Context context;


    public TodoListViewAdapter(ArrayList todoList, Context context) {
        this.todoList = todoList;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.todo_view_holder, parent, false);

        view.setClickable(true);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                displayDialog();
                return false;
            }
        });

        return new TodoListViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        holder.todoNumber.setText(Integer.toString(position + 1));
        holder.todoName.setText(todoList.get(position));

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    private void displayDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete TODO")
                .setMessage("Do you want to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTodo();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }

    private void deleteTodo() {
    }


}
