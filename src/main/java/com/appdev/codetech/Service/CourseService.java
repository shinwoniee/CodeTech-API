package com.appdev.codetech.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.CourseEntity;
import com.appdev.codetech.Repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    CourseRepository crepo;

    public CourseEntity insertCourse(CourseEntity course) {
        return crepo.save(course);
    }

    public List<CourseEntity> getAllCourse() {
        return crepo.findAll();
    }

    @SuppressWarnings("finally")
    public CourseEntity updateCourse(int cid, CourseEntity newCourseDetails) {
        CourseEntity course = new CourseEntity();
        try {
            course = crepo.findById(cid).get();
            course.setTitle(newCourseDetails.getTitle());
            course.setDesc(newCourseDetails.getDesc());
            course.setDlevel(newCourseDetails.getDlevel());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Course " + cid + "does not exist!");
        } finally {
            return crepo.save(course);
        }
    }

    public String deleteCourse(int cid) {
        String msg = "";

        if (crepo.findById(cid) != null) {
            crepo.deleteById(cid);
            msg = "Course " + cid + " is successfully deleted";
        } else
            msg = "Course " + cid + " does not exist";
        return msg;
    }

}
