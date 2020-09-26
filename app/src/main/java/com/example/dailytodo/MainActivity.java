package com.example.dailytodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
//UI
    private TextView txtDate;
    private RecyclerView dailyTodoList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton btnNewTodo;
    private FloatingActionButton btnDeleteAll;
//vars
    private DateFormat dateFormat;
    private TodoManager todoManager;
    private ArrayList<String> todoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Date
        Calendar calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MMM d yyyy");
        Date date = calendar.getTime();

        txtDate = findViewById(R.id.txt_date);
        txtDate.setText(dateFormat.format(date));

//TODO_List
        todoManager = new TodoManager(this);

//UI init
        dailyTodoList = findViewById(R.id.daily_todo_list);
        btnNewTodo = findViewById(R.id.btn_new_todo);
        btnDeleteAll = findViewById(R.id.btn_delete_all);

//Listeners setup
        btnNewTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTodoDialog();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllDialog();
            }
        });

        if (todoManager.getTodoList().size() != 0) {
            todoList = todoManager.getTodoList();
            updateToDoList();
        }

//Recycler setup
        layoutManager = new LinearLayoutManager(getApplicationContext());
        dailyTodoList.setLayoutManager(layoutManager);
        adapter = new TodoListViewAdapter(todoList, this);
        dailyTodoList.setAdapter(adapter);
    }

    private void deleteAllDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete TODO")
                .setMessage("Do you want to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteAllTodo();
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

    private void deleteAllTodo() {
        todoList.clear();
        todoManager.deleteAllTodo();
        updateToDoList();
    }

    private void updateToDoList() {
        dailyTodoList.setAdapter(new TodoListViewAdapter(todoManager.getTodoList(), getApplicationContext()));
    }

    private void addNewTodoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editTextAddNew = new EditText(this);
        builder.setTitle("Add New TODO here...")
                .setView(editTextAddNew)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todoList.add(editTextAddNew.getText().toString());
                        todoManager.saveTodoList(todoList);
                        updateToDoList();
                    }
                })
                .create()
                .show();
    }
}