package com.appdev.codetech.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblcontactus")
public class ContactUsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactusid;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "message")
    private String message;
    @Column(name = "isDelete")
    private boolean isDelete;

    public ContactUsEntity() {
    }

    public ContactUsEntity(int contactusid, String name, String email, String message, boolean isDelete) {
        this.contactusid = contactusid;
        this.name = name;
        this.email = email;
        this.message = message;
        this.isDelete = isDelete;
    }

    public int getContactusid() {
        return this.contactusid;
    }

    public void setContactusid(int contactusid) {
        this.contactusid = contactusid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
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

}
