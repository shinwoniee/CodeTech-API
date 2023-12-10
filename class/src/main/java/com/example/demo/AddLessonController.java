package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@CrossOrigin(origins = "http://localhost:3000") 
public class AddLessonController {

    @Autowired
    private AddLessonService addLessonService;

    @PostMapping("/addLesson")
    public ResponseEntity<?> addLesson(@RequestBody AddLessonEntity lesson) {
        if (lesson.getTitle() == null || lesson.getContent() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        AddLessonEntity addedLesson = addLessonService.addLesson(lesson);
        return ResponseEntity.ok(addedLesson);
    }

    @GetMapping("/getAllLessons")
    public List<AddLessonEntity> getAllLessons() {
        return addLessonService.getAllLessons();
    }

    @GetMapping("/getLesson/{lid}")
    public ResponseEntity<AddLessonEntity> getLessonById(@PathVariable int lid) {
        AddLessonEntity lesson = addLessonService.getLessonById(lid);
        if (lesson != null) {
            return ResponseEntity.ok(lesson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateLesson/{lid}")
    public ResponseEntity<AddLessonEntity> updateLesson(@PathVariable int lid, @RequestBody AddLessonEntity updatedLesson) {
        AddLessonEntity lesson = addLessonService.updateLesson(lid, updatedLesson);
        if (lesson != null) {
            return ResponseEntity.ok(lesson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteLesson/{lid}")
    public ResponseEntity<String> deleteLesson(@PathVariable int lid) {
        String result = addLessonService.deleteLesson(lid);
        return ResponseEntity.ok(result);
    }
}
