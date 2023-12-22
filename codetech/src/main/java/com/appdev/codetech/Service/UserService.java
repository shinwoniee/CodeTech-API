package com.appdev.codetech.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.ClassApplicationEntity;
import com.appdev.codetech.Entity.TicketEntity;
import com.appdev.codetech.Entity.UserEntity;
import com.appdev.codetech.Entity.VerificationCode;
import com.appdev.codetech.Repository.ClassApplicationRepository;
import com.appdev.codetech.Repository.TicketRepository;
import com.appdev.codetech.Repository.UserRepository;
import com.appdev.codetech.Repository.VerificationCodeRepository;

@Service
public class UserService {
    @Autowired
    UserRepository urepo;
    @Autowired
    TicketRepository trepo;
    @Autowired
    ClassApplicationRepository crepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;

    // @Autowired
    // private VerificationCodeRepository codeRepository;

    // public void storeVerificationCode(String email, String code) {
    // VerificationCode verificationCode = new VerificationCode(email, code);
    // codeRepository.save(verificationCode);
    // }

    // public String getVerificationCode(String email) {
    // Optional<VerificationCode> optionalCode = codeRepository.findById(email);
    // return optionalCode.map(VerificationCode::getCode).orElse(null);
    // }

    public class VerificationCodeService {

        // Assuming you have a cache implementation, you can use a Map for simplicity
        private Map<String, String> verificationCodeCache = new HashMap<>();

        // Other methods...

        public void storeVerificationCode(String email, String code) {
            // Store the verification code in the cache
            verificationCodeCache.put(email, code);
        }

        public String getVerificationCode(String email) {
            // Retrieve the verification code from the cache
            return verificationCodeCache.get(email);
        }
    }

    public UserEntity getUserByUserId(int userid) {
        Optional<UserEntity> userOptional = urepo.findById(userid);
        return userOptional.orElse(null);
    }

    public String sendVerificationCode(String to, String verificationCode) {
        try {
            // Send verification code to user's email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("shannnandreibanana@gmail.com"); // replace with your email
            message.setTo(to);
            message.setSubject("Verification Code");
            message.setText("Your verification code is: " + verificationCode);

            javaMailSender.send(message);

            // Return the verification code
            return verificationCode;
        } catch (Exception e) {
            // Handle exceptions, log errors, etc.
            throw new RuntimeException("Failed to send verification code. Please try again.", e);
        }
    }

    public UserEntity insertUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            user.setPassword(passwordEncoder.encode(newUserEntityDetails.getPassword()));
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

            if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
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
