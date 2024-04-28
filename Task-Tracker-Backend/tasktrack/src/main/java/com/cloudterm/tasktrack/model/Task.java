package com.cloudterm.tasktrack.model;


import java.sql.Date;

public class Task {
    private int task_id;
    private int user_id;
    private String task;
    private String description;
    private int priority;
    private String due_date;

    public Task() {
    }

    public Task(int task_id, String task, String description, int priority, String due_date) {
        this.task_id = task_id;
//        this.user_id = user_id;
        this.task = task;
        this.description = description;
        this.priority = priority;
        this.due_date = due_date;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
