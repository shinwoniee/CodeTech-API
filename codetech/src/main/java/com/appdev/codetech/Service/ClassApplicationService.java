package com.appdev.codetech.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.ClassApplicationEntity;
import com.appdev.codetech.Repository.ClassApplicationRepository;

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

    public ClassApplicationEntity updateClassApplicationEntity(String classCode,
            ClassApplicationEntity newClassDetails) {
        try {
            ClassApplicationEntity existingClass = classRepository.findById(classCode).orElseThrow(
                    () -> new NoSuchElementException("Class code " + classCode + " does not exist!"));

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

    public String joinClass(String classCode) {
        // Add logic to join the class based on the class code
        ClassApplicationEntity classEntity = classRepository.findById(classCode).orElse(null);

        if (classEntity != null) {
            // Add logic to associate the user with the class if needed
            // For now, let's assume a simple success message.
            return "Joined class with code " + classCode + " successfully.";
        } else {
            return null; // Indicate that the class with the given code does not exist
        }
    }

    public ClassApplicationEntity getClassByCode(String classCode) {
        return classRepository.findById(classCode).orElse(null);
    }

    public boolean classExists(String classCode) {
        return classRepository.existsById(classCode);
    }

}
