package com.appdev.codetech.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblGoals")
public class UserGoalsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int sid;
	
	@Column(name="goals")
	private String goals;

    

	public UserGoalsEntity() {
		super();
	}

	public UserGoalsEntity(int sid, String goals) {
		super();
		this.sid = sid;
		this.goals = goals;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getUserGoals() {
		return goals;
	}

	public void setUserGoals(String goals) {
		this.goals = goals;
	}
	
}
