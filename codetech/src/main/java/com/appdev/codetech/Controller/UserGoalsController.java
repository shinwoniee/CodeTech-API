package com.appdev.codetech.Controller;

import com.appdev.codetech.Entity.CreateGoalRequest;
import com.appdev.codetech.Entity.UserGoalsEntity;
import com.appdev.codetech.Service.UserGoalsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userGoals")
public class UserGoalsController {

    @Autowired
    UserGoalsService sserv;

    // Create a student record
    @PostMapping("/insertUserGoals")
    public UserGoalsEntity insertUserGoals(@RequestBody CreateGoalRequest createGoalRequest) {
    UserGoalsEntity goals = createGoalRequest.getGoals();
    String course = createGoalRequest.getCourse();
    goals.setGoalStatus("incomplete");
    goals.setGoalCourse(course);
    UserGoalsEntity savedGoals = sserv.insertUserGoals(goals);
    return savedGoals;
    }

    // Read
    @GetMapping("/getAllUserGoals")
    public List<UserGoalsEntity> getAllUserGoals() {
        return sserv.getAllUserGoals();
    }

    // Update
    @PutMapping("/updateUserGoals")
    public UserGoalsEntity updateUserGoals(@RequestParam int sid, @RequestBody UserGoalsEntity newUserGoals) {
        return sserv.updateUserGoals(sid, newUserGoals);
    }

    @PutMapping("/updateGoalStatus")
    public UserGoalsEntity updateGoalStatus(@RequestParam int sid, @RequestBody UserGoalsEntity newGoalStatus) {
        return sserv.updateGoalStatus(sid, newGoalStatus);
    }

    // Delete
    @DeleteMapping("/deleteUserGoals/{sid}")
    public String deleteUserGoals(@PathVariable int sid) {
        return sserv.deleteUserGoals(sid);
    }

}