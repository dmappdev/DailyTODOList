package com.example.dailytodo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TodoManager {

//    private static final String todoDataKey = "TODO_DATA_KEY";
    private String name;
//    private TodoItem todoItem;
    private SharedPreferences sharedPreferences;
    private Context context;

    public TodoManager(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

//    String[] data =  {"TODO1", "TODO2", "TODO3", "TODO4"};
//    ArrayList<String> todoList;

    public void saveTodoList(ArrayList todoList){

        for (int i = 0; i < todoList.size(); i++) {

            sharedPreferences.edit().putString(Integer.toString(i), todoList.get(i).toString()).commit();

        }
    }

    public ArrayList getTodoList(){
        ArrayList<String> todoList = new ArrayList<>();

        for (Map.Entry<String, ?> pair: sharedPreferences.getAll().entrySet() ) {
            todoList.add(pair.getValue().toString());
        }
        return todoList;
    }

    public void deleteAllTodo(){
        sharedPreferences.edit().clear().commit();
    }

}
