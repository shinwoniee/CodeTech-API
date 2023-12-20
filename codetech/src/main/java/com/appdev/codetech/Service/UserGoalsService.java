package com.appdev.codetech.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.UserGoalsEntity;
import com.appdev.codetech.Repository.UserGoalsRepository;

@Service
public class UserGoalsService {
	@Autowired
	UserGoalsRepository srepo;
	
	//Create
	public UserGoalsEntity insertUserGoals(UserGoalsEntity goals) {
		return srepo.save(goals);//belongs to JPA Repository
	}
	
	//Read
	public List<UserGoalsEntity> getAllUserGoals(){
		return srepo.findAll();
	}
	
	//Update
	@SuppressWarnings("finally")
	public UserGoalsEntity updateUserGoals(int sid, UserGoalsEntity newUserGoals) {
		UserGoalsEntity goals = new UserGoalsEntity();
		try {
			//search ID number
			goals = srepo.findById(sid).get();
			
			//update
			goals.setUserGoals(newUserGoals.getUserGoals());
			
		}catch(NoSuchElementException ex) {
			throw new NoSuchElementException("Goal " + sid + "does not exist!");
		}finally {
			return srepo.save(goals);
		}
	}

	public UserGoalsEntity updateGoalStatus(int sid, UserGoalsEntity newGoalStatus) {
		try {
			// Search ID number
			UserGoalsEntity status = srepo.findById(sid).orElseThrow(() -> new NoSuchElementException("Status " + sid + " does not exist!"));
	
			// Update status
			status.setGoalStatus(newGoalStatus.getGoalStatus());
	
			// Save the updated status entity
			return srepo.save(status);
		} catch (Exception ex) {
			// Handle exceptions if needed
			ex.printStackTrace(); // Log the exception or handle it accordingly
			return null; // Consider returning a meaningful response in case of failure
		}
	}
	
	//Delete
	public String deleteUserGoals(int sid) {
		String msg = "";
		
		if (srepo.findById(sid) != null) {
			srepo.deleteById(sid);
			msg = "Goal " +sid+ " is successfully deleted!";
		}else
			msg = "Goal " +sid+ " does not exist.";
		return msg;
	}
	
}