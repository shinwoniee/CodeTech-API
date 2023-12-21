package com.appdev.codetech.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblcreateclass")
public class ClassApplicationEntity {

    @Id
    @Column(name = "classcode", unique = true)
    private String classcode;

    @Column(name = "classname")
    private String classname;
    @Column(name = "classdescription")
    private String classdescription;

    public ClassApplicationEntity() {
        super();
    }

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    public ClassApplicationEntity(String classcode, String classname, String classdescription, UserEntity user) {
        this.classcode = classcode;
        this.classname = classname;
        this.classdescription = classdescription;
        this.user = user;
    }

    public String getClasscode() {
        return this.classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassdescription() {
        return this.classdescription;
    }

    public void setClassdescription(String classdescription) {
        this.classdescription = classdescription;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
