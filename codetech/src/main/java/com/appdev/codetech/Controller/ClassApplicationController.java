package com.appdev.codetech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appdev.codetech.Entity.ClassApplicationEntity;
import com.appdev.codetech.Service.ClassApplicationService;

import java.util.List;

@RestController
@RequestMapping("/createClass")
@CrossOrigin(origins = ("http://localhost:3000"))

public class ClassApplicationController {

    @Autowired
    private ClassApplicationService sserv;

    @GetMapping("/getClassbyId")
    public List<ClassApplicationEntity> getClassesByUserId(@RequestParam int userid) {
        return sserv.getClassesByUserId(userid);
    }

    @PostMapping("/insertClass")
    public ResponseEntity<Object> insertClassApplicationEntity(@RequestBody ClassApplicationEntity classEntity) {
        String classCode = classEntity.getClasscode();
        String className = classEntity.getClassname();
        String classDescription = classEntity.getClassdescription();

        // Check if required fields are null
        if (classCode == null || className == null || classDescription == null) {
            return ResponseEntity.badRequest().body("Class code, name, and description cannot be null.");
        }

        // Check if a class with the given code already exists
        if (sserv.classExists(classCode)) {
            return ResponseEntity.badRequest().body("Class with code " + classCode + " already exists.");
        }

        // If the class code is unique and required fields are not null, proceed with
        // insertion
        ClassApplicationEntity insertedClass = sserv.insertClassApplicationEntity(classEntity);
        return ResponseEntity.ok(insertedClass);
    }

    @GetMapping("/getClass")
    public List<ClassApplicationEntity> getAllClasses() {
        return sserv.getAllClasses();
    }

    @PutMapping("/updateClass/{classcode}")
    public ClassApplicationEntity updateClassApplicationEntity(@PathVariable String classcode,
            @RequestBody ClassApplicationEntity newClass) {
        return sserv.updateClassApplicationEntity(classcode, newClass);
    }

    @DeleteMapping("/deleteClass/{classcode}")
    public String deleteClass(@PathVariable String classcode) {
        return sserv.deleteClass(classcode);
    }

    @GetMapping("/getClassByCode/{classcode}")
    public ResponseEntity<ClassApplicationEntity> getClassByCode(@PathVariable String classcode) {
        ClassApplicationEntity classEntity = sserv.getClassByCode(classcode);
        if (classEntity != null) {
            return ResponseEntity.ok(classEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/joinClass/{classcode}")
    public ResponseEntity<String> joinClass(@PathVariable String classcode) {
        String joinResult = sserv.joinClass(classcode);
        if (joinResult != null) {
            return ResponseEntity.ok(joinResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
