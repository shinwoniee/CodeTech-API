package com.appdev.codetech.Entity;

public class CreateGoalRequest {
    private UserGoalsEntity goals;
    private String course;
    private String status;

    // Getters and setters

    public UserGoalsEntity getGoals() {
        return goals;
    }

    public void setGoals(UserGoalsEntity goals) {
        this.goals = goals;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
