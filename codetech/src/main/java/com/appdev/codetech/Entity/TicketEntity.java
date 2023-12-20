package com.appdev.codetech.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketid;

    @Column(name = "title")
    private String title;
    @Column(name = "email")
    private String email;
    @Column(name = "category")
    private String category;
    @Column(name = "details")
    private String details;
    @Column(name = "status")
    private String status;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "isDelete")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    public TicketEntity() {
    }

    public TicketEntity(int ticketid, String title, String email, String category, String details, String status,
            LocalDateTime timestamp, boolean isDelete, UserEntity user) {
        this.ticketid = ticketid;
        this.title = title;
        this.email = email;
        this.category = category;
        this.details = details;
        this.status = status;
        this.timestamp = timestamp;
        this.isDelete = isDelete;
        this.user = user;
    }

    public int getTicketid() {
        return this.ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isIsDelete() {
        return this.isDelete;
    }

    public boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
