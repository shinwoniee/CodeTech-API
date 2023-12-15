package com.appdev.codetech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdev.codetech.Entity.CourseEntity;
import com.appdev.codetech.Service.CourseService;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = ("http://localhost:3000"))
public class CourseController {

    @Autowired
    CourseService cserv;

    @GetMapping("/getCourse")
    public List<CourseEntity> getAllCourse() {
        return cserv.getAllCourse();
    }

    @PostMapping("/insertCourse")
    public CourseEntity insertCourse(@RequestBody CourseEntity course) {
        return cserv.insertCourse(course);
    }

    @PutMapping("/updateCourse")
    public CourseEntity updateCourse(@RequestParam int cid, @RequestBody CourseEntity newCourseDetails) {
        return cserv.updateCourse(cid, newCourseDetails);
    }

    @DeleteMapping("/deleteCourse/{cid}")
    public String deleteCourse(@PathVariable int cid) {
        return cserv.deleteCourse(cid);
    }
}
