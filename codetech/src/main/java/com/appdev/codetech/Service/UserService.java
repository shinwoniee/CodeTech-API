package com.appdev.codetech.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.UserEntity;
import com.appdev.codetech.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository urepo;

    public UserEntity insertUser(UserEntity user) {
        return urepo.save(user);
    }

    public boolean checkEmailExists(String email) {
        try {
            Optional<UserEntity> existingUser = urepo.findByEmail(email);
            return existingUser.isPresent();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return false or handle the exception as appropriate for your application
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        try {
            Optional<UserEntity> existingUser = urepo.findByUsername(username);
            return existingUser.isPresent();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return false or handle the exception as appropriate for your application
            return false;
        }
    }

    public List<UserEntity> getAllUsers() {
        return urepo.findAll();
    }

    public UserEntity updateUser(int userid, UserEntity newUserEntityDetails) {
        UserEntity user = new UserEntity();
        try {
            user = urepo.findById(userid).get();
            user.setUsername(newUserEntityDetails.getUsername());
            user.setPassword(newUserEntityDetails.getPassword());
            user.setFirstname(newUserEntityDetails.getFirstname());
            user.setLastname(newUserEntityDetails.getLastname());
            user.setRole(newUserEntityDetails.getRole());
            user.setIsDeleted(newUserEntityDetails.getIsDelete());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User: " + userid + " does not exist!");
        }

        return urepo.save(user);

    }

    public UserEntity removeUser(int userid) {
        UserEntity user = new UserEntity();
        try {
            user = urepo.findById(userid).get();
            user.setUsername(null);
            user.setPassword(null);
            user.setFirstname(null);
            user.setLastname(null);
            user.setRole(null);
            user.setIsDeleted(true);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User: " + userid + " does not exist!");
        }

        return urepo.save(user);

    }

    public AuthenticationResult authenticateUser(String usernameOrEmail, String password) {
        try {
            Optional<UserEntity> user;

            // Check if the provided usernameOrEmail is a valid email
            if (usernameOrEmail.contains("@")) {
                user = urepo.findByEmail(usernameOrEmail);
            } else {
                user = urepo.findByUsername(usernameOrEmail);
            }

            if (user.isPresent() && user.get().getPassword().equals(password)) {
                AuthenticationResult result = new AuthenticationResult(true, false, false, user.get());
                return result;
            } else {
                boolean invalidPassword = false;
                boolean usernameOrEmailNotFound = false;

                if (user.isPresent()) {
                    // User found, but password is incorrect
                    invalidPassword = true;
                } else {
                    // User not found
                    usernameOrEmailNotFound = true;
                }

                return new AuthenticationResult(false, invalidPassword, usernameOrEmailNotFound, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AuthenticationResult(false, true, false, null);
        }
    }

    public String deleteUser(int userid) {
        String msg = "";

        if (urepo.findById(userid) != null) {
            urepo.deleteById(userid);
            msg = "User " + userid + " is successfully deleted!";
        } else
            msg = "User " + userid + " does not exist";

        return msg;
    }
}
