package com.appdev.codetech.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.AddLessonEntity;
import com.appdev.codetech.Repository.AddLessonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddLessonService {

    @Autowired
    private AddLessonRepository addLessonRepository;

    public AddLessonEntity addLesson(AddLessonEntity lesson) {
        // You can add any business logic here before saving the lesson to the database
        return addLessonRepository.save(lesson);
    }

    public List<AddLessonEntity> getAllLessons() {
        return addLessonRepository.findAll();
    }

    public AddLessonEntity getLessonById(int lid) {
        Optional<AddLessonEntity> optionalLesson = addLessonRepository.findById(lid);
        return optionalLesson.orElse(null);
    }

    public AddLessonEntity updateLesson(int lid, AddLessonEntity updatedLesson) {
        Optional<AddLessonEntity> optionalLesson = addLessonRepository.findById(lid);
        if (optionalLesson.isPresent()) {
            // Update the existing lesson with the new values
            AddLessonEntity existingLesson = optionalLesson.get();
            existingLesson.setTitle(updatedLesson.getTitle());
            existingLesson.setContent(updatedLesson.getContent());
            // You can add more fields to update based on your requirements
            return addLessonRepository.save(existingLesson);
        } else {
            return null;
        }
    }

    public String deleteLesson(int lid) {
        Optional<AddLessonEntity> optionalLesson = addLessonRepository.findById(lid);
        if (optionalLesson.isPresent()) {
            addLessonRepository.deleteById(lid);
            return "Lesson with ID " + lid + " deleted successfully";
        } else {
            return "Lesson with ID " + lid + " not found";
        }
    }
}
