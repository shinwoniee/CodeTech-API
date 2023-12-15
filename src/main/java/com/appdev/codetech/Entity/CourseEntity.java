package com.appdev.codetech.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblCourses")
public class CourseEntity {

    @Id
    @Column(name = "courseID")
    private int cid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desc;

    @Column(name = "difficultylevel")
    private String dlevel;

    public CourseEntity() {
    }

    public CourseEntity(int cid, String title, String desc, String dlevel) {
        this.cid = cid;
        this.title = title;
        this.desc = desc;
        this.dlevel = dlevel;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDlevel() {
        return dlevel;
    }

    public void setDlevel(String dlevel) {
        this.dlevel = dlevel;
    }

}
