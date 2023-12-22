package com.appdev.codetech.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.ClassApplicationEntity;
import com.appdev.codetech.Entity.TicketEntity;
import com.appdev.codetech.Entity.UserEntity;
import com.appdev.codetech.Repository.ClassApplicationRepository;
import com.appdev.codetech.Repository.TicketRepository;
import com.appdev.codetech.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository urepo;
    @Autowired
    TicketRepository trepo;
    @Autowired
    ClassApplicationRepository crepo;

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

        Optional<UserEntity> userOptional = urepo.findById(userid);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Check if there are associated tickets
            long ticketCount = trepo.countByUser(user);
            long classCount = crepo.countByUser(user);

            if (classCount > 0 && ticketCount > 0) {
                trepo.deleteByUser(user);
                crepo.deleteByUser(user);
                urepo.deleteById(userid);
                return "User " + userid + " and associated tickets and classes are successfully deleted!";
            } else if (ticketCount > 0) {
                trepo.deleteByUser(user);
                urepo.deleteById(userid);
                return "User " + userid + " and associated tickets are successfully deleted!";

            } else if (classCount > 0) {
                crepo.deleteByUser(user);
                urepo.deleteById(userid);
                return "User " + userid + " and associated classes are successfully deleted!";

            }

            urepo.deleteById(userid);
            msg = "User " + userid + " is successfully deleted!";

        } else {
            msg = "User " + userid + " does not exist";
        }

        return msg;
    }

    public UserEntity removeUser(int userid) {
        UserEntity user = new UserEntity();

        try {
            user = urepo.findById(userid)
                    .orElseThrow(() -> new NoSuchElementException("User: " + userid + " does not exist!"));

            // Set associated tickets to null and update isDelete to true
            List<TicketEntity> tickets = user.getTickets();
            if (tickets != null) {
                for (TicketEntity ticket : tickets) {
                    ticket.setTitle(null);
                    ticket.setCategory(null);
                    ticket.setEmail(null);
                    ticket.setDetails(null);
                    ticket.setStatus(null);
                    ticket.setTimestamp(null);
                    ticket.setUser(null);
                    ticket.setIsDelete(true);
                }
            }

            List<ClassApplicationEntity> classes = user.getClasses();
            if (classes != null) {
                for (ClassApplicationEntity classs : classes) {
                    classs.setClassdescription(null);
                    classs.setClassname(null);
                    classs.setUser(null);
                }
            }

            // Set user fields to null and update isDelete to true
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

    public long getCountByRole(String role) {
        return urepo.countByRole(role);
    }
}
