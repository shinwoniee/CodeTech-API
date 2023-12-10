package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = ("http://localhost:3000"))
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService quizQuestionService;

    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody QuizQuestionEntity quizQuestion) {
        // Check for required fields
        if (quizQuestion.getTitle() == null || quizQuestion.getQuestion() == null ||
            quizQuestion.getOptionA() == null || quizQuestion.getOptionB() == null ||
            quizQuestion.getOptionC() == null || quizQuestion.getOptionD() == null ||
            quizQuestion.getCorrectAnswer() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        QuizQuestionEntity addedQuestion = quizQuestionService.addQuestion(quizQuestion);
        return ResponseEntity.ok(addedQuestion);
    }

    @GetMapping("/getAllQuestions")
    public List<QuizQuestionEntity> getAllQuestions() {
        return quizQuestionService.getAllQuestions();
    }

    @GetMapping("/getQuestion/{qid}")
    public ResponseEntity<QuizQuestionEntity> getQuestionById(@PathVariable int qid) {
        QuizQuestionEntity question = quizQuestionService.getQuestionById(qid);
        if (question != null) {
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateQuestion/{qid}")
    public ResponseEntity<QuizQuestionEntity> updateQuestion(@PathVariable int qid, @RequestBody QuizQuestionEntity updatedQuestion) {
        QuizQuestionEntity question = quizQuestionService.updateQuestion(qid, updatedQuestion);
        if (question != null) {
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteQuestion/{qid}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int qid) {
        String result = quizQuestionService.deleteQuestion(qid);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
