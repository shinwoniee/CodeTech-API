package com.appdev.codetech.Controller;

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

        AuthenticationResult result = userv.authenticateUser(usernameOrEmail, password);

        Map<String, Object> response = new HashMap<>();

        if (result.isSuccess()) {
            response.put("user", result.getUser());
            return ResponseEntity.ok(response);
        } else {
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
    public ResponseEntity<Map<String, Object>> checkEmail(@PathVariable String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> result = userv.checkEmailExists(email);
            response.put("exists", result.get("exists"));
            response.put("userid", result.get("userid"));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("exists", false);
            response.put("userid", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checkUsername/{username}")
    public ResponseEntity<Map<String, Object>> checkUsername(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> result = userv.checkUsernameExists(username);
            response.put("exists", result.get("exists"));
            response.put("userid", result.get("userid"));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("exists", false);
            response.put("userid", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
