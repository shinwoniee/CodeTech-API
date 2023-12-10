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