package com.cloudterm.tasktrack.repository;

import com.cloudterm.tasktrack.model.User;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import static com.cloudterm.tasktrack.repository.DbConstants.*;

@Repository
public class UserRepository {

    public static Boolean createUserTable(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();

            // Create the table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "full_name VARCHAR(50)," +
                    "email VARCHAR(100) UNIQUE," +
                    "password VARCHAR(200)" +
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

    public static Boolean insertUser(User user){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            createUserTable();

            String hashedPassword = hashMD5Algorithm(user.getPassword());

            String insertUser = "INSERT INTO sys.users (full_name, email, password) " +
                    "VALUES (?, ?, ?)";;
            PreparedStatement insertStmt = connection.prepareStatement(insertUser);
            insertStmt.setString(1, user.getFull_name());
            insertStmt.setString(2, user.getEmail());
            insertStmt.setString(3, hashedPassword);

            int rowsAffected = insertStmt.executeUpdate();

            insertStmt.close();
            connection.close();

            if (rowsAffected > 0) {
                System.out.println("User added successfully: " + user.getFull_name());
                return true;
            } else {
                System.out.println("Failed to add user: " + user.getFull_name());
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static Boolean validateUser(String email, String password){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            String hashedInputPassword = hashMD5Algorithm(password);

            String query = "SELECT * FROM sys.users WHERE email=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, hashedInputPassword);
            ResultSet resultSet = statement.executeQuery();

            boolean isValidUser = resultSet.next();

            resultSet.close();
            statement.close();
            connection.close();

            return isValidUser;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static String getUserName(String email){
        String name = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            String query = "SELECT full_name FROM sys.users WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                name = resultSet.getString("full_name");
            }

            resultSet.close();
            statement.close();
            connection.close();

            return name;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return name;
    }

    public static int getUserId(String email){
        int id = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(RDS_URL, USERNAME, PASSWORD);

            String query = "SELECT user_id FROM sys.users WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("user_id");
            }

            resultSet.close();
            statement.close();
            connection.close();

            return id;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return id;
    }

    public static String hashMD5Algorithm(String input)
    {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
