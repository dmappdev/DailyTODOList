package com.example.dailytodo;

public class TodoItem {

    private String name;
    private Boolean done;

    public TodoItem(String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDone(){
        return done;
    }
}
