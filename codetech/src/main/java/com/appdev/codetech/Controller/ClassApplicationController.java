package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/createClass")
@CrossOrigin(origins = ("http://localhost:3000"))

public class ClassApplicationController {
    @Autowired
    private ClassApplicationService sserv;

    @PostMapping("/insertClass")
    public ClassApplicationEntity insertClassApplicationEntity(@RequestBody ClassApplicationEntity classEntity) {
        return sserv.insertClassApplicationEntity(classEntity);
    }

    @GetMapping("/getClass")
    public List<ClassApplicationEntity> getAllClasses() {
        return sserv.getAllClasses();
    }

    @PutMapping("/updateClass/{classcode}")
    public ClassApplicationEntity updateClassApplicationEntity(@PathVariable String classcode, @RequestBody ClassApplicationEntity newClass) {
        return sserv.updateClassApplicationEntity(classcode, newClass);
    }

    @DeleteMapping("/deleteClass/{classcode}")
    public String deleteClass(@PathVariable String classcode){
        return sserv.deleteClass(classcode);
    }
}
