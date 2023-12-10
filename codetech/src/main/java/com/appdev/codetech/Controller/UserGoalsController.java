package com.appdev.codetech.Controller;

import com.appdev.codetech.Entity.UserGoalsEntity;
import com.appdev.codetech.Service.UserGoalsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RestController//where the mapping happens, when you want to check the output via browser
@RequestMapping("/userGoals")// parent mapping
public class UserGoalsController {

    @Autowired
    UserGoalsService sserv;

    //Create a student record
    @PostMapping("/insertUserGoals")//If you are inserting
    public UserGoalsEntity insertUserGoals(@RequestBody UserGoalsEntity goals){//Request body is Annotation for??
        return sserv.insertUserGoals(goals);
    }

    //Read
    @GetMapping("/getAllUserGoals")
    public List<UserGoalsEntity> getAllUserGoals(){
        return sserv.getAllUserGoals();
    }
    
    //Update
    @PutMapping("/updateUserGoals")
	public UserGoalsEntity updateUserGoals(@RequestParam int sid, @RequestBody UserGoalsEntity newUserGoals) {
		return sserv.updateUserGoals(sid, newUserGoals);
	}
    
    //Delete
    @DeleteMapping("/deleteUserGoals/{sid}")
    public String deleteUserGoals(@PathVariable int sid) {
    	return sserv.deleteUserGoals(sid);
    }

}