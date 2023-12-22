package com.appdev.codetech.Controller;

import java.security.SecureRandom;
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
import com.appdev.codetech.Service.VerificationCodeService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userv;

    @Autowired
    private VerificationCodeService verificationCodeService;

    private String generateVerificationCode() {
        // Generate a random alphanumeric verification code
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }

        return code.toString();
    }

    @PostMapping("/sendVerificationCode")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        try {
            // You can add additional validation for the email if needed

            // Generate verification code
            String verificationCode = generateVerificationCode();

            // Store the verification code in the cache
            verificationCodeService.storeVerificationCode(email, verificationCode);

            // Send verification code to user's email
            userv.sendVerificationCode(email, verificationCode);

            // Return the verification code to the frontend
            return ResponseEntity.ok(verificationCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send verification code. Please try again.");
        }
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String enteredCode) {
        try {
            // Retrieve the stored verification code from the cache
            String storedCode = verificationCodeService.getVerificationCode(email);

            // Check if the entered code matches the stored code
            if (enteredCode.equals(storedCode)) {
                // Code verification successful
                return ResponseEntity.ok("success");
            } else {
                // Code verification failed
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect verification code");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to verify code. Please try again.");
        }
    }

    @PostMapping("/insertUser")
    public UserEntity insertUser(@RequestBody UserEntity user) {
        UserEntity savedUser = userv.insertUser(user);
        return savedUser;
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

    @DeleteMapping("/deleteUser/{userid}")
    public String deleteUser(@PathVariable int userid) {
        return userv.deleteUser(userid);
    }

    @GetMapping("/print")
    public String printHello() {
        return "hellooo";
    }

    @GetMapping("/getUser/{userid}")
    public ResponseEntity<UserEntity> getUserByUserId(@PathVariable int userid) {
        try {
            UserEntity user = userv.getUserByUserId(userid);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
