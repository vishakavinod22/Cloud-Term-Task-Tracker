package com.cloudterm.tasktrack.controller;

import com.cloudterm.tasktrack.model.User;
import com.cloudterm.tasktrack.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @PostMapping(value = "/test/user")
    public JSONObject test() {
        JSONObject result = new JSONObject();

        result.put("message", "User test is a success!");
        return result;
    }

    // Create a users table
    @PostMapping(value = "/create/table/users")
    public JSONObject createTable() {
        JSONObject result = new JSONObject();

        Boolean isTableCreated = UserRepository.createUserTable();
        if(isTableCreated){
            result.put("message", "Success.");
        } else {
            result.put("message", "Fail.");
        }
        return result;
    }

    // Insert user into the database
    @PostMapping(value = "/insert/user")
    public JSONObject userSignIn(@RequestBody User user) {
        JSONObject result = new JSONObject();

        if(user.getFull_name().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()){
            result.put("message", "Incorrect input");
            return result;
        }

        Boolean isUserAdded = UserRepository.insertUser(user);
        if(isUserAdded){
            result.put("message", "Success.");
        } else {
            result.put("message", "Fail.");
        }
        return result;
    }

    // validate user
    @PostMapping(value = "/login")
    public JSONObject userLogin(@RequestBody User user) {
        JSONObject result = new JSONObject();

        String email = user.getEmail();
        String password = user.getPassword();
        int user_id = 0;
        String name = "";

        if(email.isEmpty() || password.isEmpty()){
            result.put("message", "Incomplete input");
            return result;
        }

        Boolean isValidUser = UserRepository.validateUser(email, password);
        if(isValidUser){
            result.put("message", "Success.");
            user_id = UserRepository.getUserId(email);
            name = UserRepository.getUserName(email);
        } else {
            result.put("message", "Invalid credentials.");
        }
        result.put("user_id", user_id);
        result.put("name", name);
        return result;
    }


}
