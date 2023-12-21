package com.appdev.codetech.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, Object> checkEmailExists(String email) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<UserEntity> existingUser = urepo.findByEmail(email);
            result.put("exists", existingUser.isPresent());
            result.put("userid", existingUser.map(UserEntity::getUserid).orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("exists", false);
            result.put("userid", null);
        }
        return result;
    }

    public Map<String, Object> checkUsernameExists(String username) {
        Map<String, Object> result = new HashMap<>();
        try {
            Optional<UserEntity> existingUser = urepo.findByUsername(username);
            result.put("exists", existingUser.isPresent());
            result.put("userid", existingUser.map(UserEntity::getUserid).orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
            result.put("exists", false);
            result.put("userid", null);
        }
        return result;
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
            user.setEmail(newUserEntityDetails.getEmail());
            user.setFirstname(newUserEntityDetails.getFirstname());
            user.setLastname(newUserEntityDetails.getLastname());
            user.setRole(newUserEntityDetails.getRole());
            user.setIsDelete(newUserEntityDetails.getIsDelete());
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
            user.setEmail(null);
            user.setFirstname(null);
            user.setLastname(null);
            user.setRole(null);
            user.setIsDelete(true);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User: " + userid + " does not exist!");
        }

        return urepo.save(user);

    }

    public AuthenticationResult authenticateUser(String usernameOrEmail, String password) {
        try {
            Optional<UserEntity> user;

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
                    invalidPassword = true;
                } else {
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

    public long getCountByRole(String role) {
        return urepo.countByRole(role);
    }
}
