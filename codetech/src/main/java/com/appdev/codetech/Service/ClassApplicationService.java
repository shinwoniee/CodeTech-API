package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClassApplicationService {

    @Autowired
    private ClassApplicationRepository classRepository;

    public ClassApplicationEntity insertClassApplicationEntity(ClassApplicationEntity newClass) {
        // Add validation logic if needed
        return classRepository.save(newClass);
    }

    public List<ClassApplicationEntity> getAllClasses() {
        return classRepository.findAll();
    }

    public ClassApplicationEntity updateClassApplicationEntity(String classCode, ClassApplicationEntity newClassDetails) {
        try {
            ClassApplicationEntity existingClass = classRepository.findById(classCode).orElseThrow(
                    () -> new NoSuchElementException("Class code " + classCode + " does not exist!")
            );

            existingClass.setClassname(newClassDetails.getClassname());
            existingClass.setClassdescription(newClassDetails.getClassdescription());
            existingClass.setClasscode(newClassDetails.getClasscode());

            return classRepository.save(existingClass);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Class code " + classCode + " does not exist!");
        }
    }

    public String deleteClass(String classCode) {
        classRepository.deleteById(classCode);
        return "Class with code " + classCode + " has been deleted.";
    }

    public List<ClassApplicationEntity> getClassApplicationEntities() {
        // Implement logic to get class application entities
        return classRepository.findAll();
    }
}
