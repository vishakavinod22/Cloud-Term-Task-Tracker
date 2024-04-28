package com.cloudterm.tasktrack.controller;

import com.cloudterm.tasktrack.model.Task;
import com.cloudterm.tasktrack.repository.TaskRepository;
import com.cloudterm.tasktrack.s3bucket.BucketName;
import com.cloudterm.tasktrack.s3bucket.FileStore;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    @PostMapping(value = "/test/task")
    public JSONObject test() {
        JSONObject result = new JSONObject();

        result.put("message", "Task test is a success!");
        return result;
    }

    // Create a task table
    @PostMapping(value = "/create/table/tasks")
    public JSONObject createTable() {
        JSONObject result = new JSONObject();

        Boolean isTableCreated = TaskRepository.createTaskTable();
        if(isTableCreated){
            result.put("message", "Success.");
        } else {
            result.put("message", "Fail.");
        }
        return result;
    }

    // Insert task into the database
    @PostMapping(value = "/insert/task")
    public JSONObject userSignIn(@RequestBody Task task) {
        JSONObject result = new JSONObject();

        if(task.getTask().isEmpty()){
            result.put("message", "Incorrect input");
            return result;
        }

        Boolean isTaskAdded = TaskRepository.insertTask(task);
        if(isTaskAdded){
            result.put("message", "Success.");
        } else {
            result.put("message", "Fail.");
        }
        return result;
    }

    // something to list the tasks
    @GetMapping(value = "/dashboard")
    public JSONObject listTasks() {
        JSONObject result = new JSONObject();
        List<Task> taskList = TaskRepository.getAllTask();
        result.put("tasks", taskList);
        return result;
    }

    @DeleteMapping(value = "/delete/task/{taskId}")
    public JSONObject deleteTask(@PathVariable int taskId) {
        JSONObject result = new JSONObject();
        boolean success = TaskRepository.deleteTask(taskId);
        result.put("success", success);
        return result;
    }


}


