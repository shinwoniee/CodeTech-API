package com.appdev.codetech.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblGoals")
public class UserGoalsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int sid;

	@Column(name = "goals")
	private String goals;

	@Column(name = "course")
	private String course;

	@Column(name = "status")
	private String status;

	public UserGoalsEntity() {
	}

	public UserGoalsEntity(int sid, String goals, String course, String status) {
		this.sid = sid;
		this.goals = goals;
		this.course = course;
		this.status = status;
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

	public String getGoalCourse() {
		return course;
	}

	public void setGoalCourse(String course) {
		this.course = course;
	}

	public String getGoalStatus() {
		return status;
	}

	public void setGoalStatus(String status) {
		this.status = status;
	}

}
