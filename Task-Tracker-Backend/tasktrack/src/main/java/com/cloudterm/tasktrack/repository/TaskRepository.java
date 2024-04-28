package com.cloudterm.tasktrack.repository;

import com.cloudterm.tasktrack.model.Task;
import com.cloudterm.tasktrack.model.User;
import com.cloudterm.tasktrack.s3bucket.BucketName;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.*;
import java.util.*;

import static com.cloudterm.tasktrack.repository.DbConstants.*;

@Repository
public class TaskRepository {

    public static Boolean createTaskTable(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();


            // Create the table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks (" +
                    "task_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT," +
                    "task VARCHAR(50) NOT NULL," +
                    "description TEXT," +
                    "priority INT," +
                    "due_date DATE" +
                    ")";

            statement.executeUpdate(createTableSQL);
            connection.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static Boolean insertTask(Task task){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            createTaskTable();

            String insertTaskSQL = "INSERT INTO sys.tasks (user_id, task, description, priority, due_date) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertTaskSQL);
            insertStmt.setInt(1, task.getUser_id());
            insertStmt.setString(2, task.getTask());
            insertStmt.setString(3, task.getDescription());
            insertStmt.setInt(4, task.getPriority());
            insertStmt.setString(5, task.getDue_date());

            int rowsAffected = insertStmt.executeUpdate();

            insertStmt.close();
            connection.close();

            if (rowsAffected > 0) {
                System.out.println("Task added successfully: " + task.getTask());
                return true;
            } else {
                System.out.println("Failed to add task: " + task.getTask());
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static List<Task> getAllTask(){
        List<Task> taskList = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            String query = "SELECT task_id, task, description, priority, due_date FROM sys.tasks";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int task_id = resultSet.getInt("task_id");
                String task = resultSet.getString("task");
                String description = resultSet.getString("description");
                int priority = resultSet.getInt("priority");
                String due_date = resultSet.getDate("due_date").toString();

                Task newTask = new Task(task_id, task, description, priority, due_date);
                taskList.add(newTask);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return taskList;
    }

    public static Boolean deleteTask(int taskId) {
        List<Task> taskList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            // Delete the task with the given task ID
            String deleteQuery = "DELETE FROM sys.tasks WHERE task_id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, taskId);
            int rowsAffected = deleteStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Task deleted successfully: ");
                return true;
            } else {
                System.out.println("Failed to delete task: ");
            }

            deleteStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
