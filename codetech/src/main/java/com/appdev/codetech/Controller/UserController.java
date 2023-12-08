package com.appdev.codetech.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdev.codetech.Entity.UserEntity;
import com.appdev.codetech.Service.AuthenticationResult;
import com.appdev.codetech.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userv;

    @PostMapping(("/insertUser"))
    public UserEntity insertUser(@RequestBody UserEntity user) {
        return userv.insertUser(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody Map<String, String> credentials) {
        String usernameOrEmail = credentials.get("usernameOrEmail");
        String password = credentials.get("password");

        // For simplicity, assuming you have a UserService method for authentication
        AuthenticationResult result = userv.authenticateUser(usernameOrEmail, password);

        Map<String, Object> response = new HashMap<>();

        if (result.isSuccess()) {
            // Authentication successful, include user information in the response
            response.put("user", result.getUser());
            return ResponseEntity.ok(response);
        } else {
            // Authentication failed, return an appropriate error message
            if (result.isInvalidPassword()) {
                response.put("error", "Invalid password");
            } else if (result.isUsernameOrEmailNotFound()) {
                response.put("error", "Username/email not registered");
            } else {
                response.put("error", "Authentication failed");
            }

            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/checkEmail/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@PathVariable String email) {
        boolean exists = userv.checkEmailExists(email);
        Map<String, Boolean> response = Collections.singletonMap("exists", exists);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/checkUsername/{username}")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@PathVariable String username) {
        boolean exists = userv.checkUsernameExists(username);
        Map<String, Boolean> response = Collections.singletonMap("exists", exists);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userv.getAllUsers();
    }

    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestParam int userid, @RequestBody UserEntity newUserEntity) {
        return userv.updateUser(userid, newUserEntity);
    }

    @PutMapping("/removeUser")
    public UserEntity removeUser(@RequestParam int userid) {
        return userv.removeUser(userid);
    }

    @DeleteMapping("/deleteUser/{empno}")
    public String deleteUser(@PathVariable int userid) {
        return userv.deleteUser(userid);
    }

    @GetMapping("/print")
    public String printHello() {
        return "hellooo";
    }
}
