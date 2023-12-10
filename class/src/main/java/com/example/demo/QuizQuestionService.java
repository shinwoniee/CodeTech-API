package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public QuizQuestionEntity addQuestion(QuizQuestionEntity newQuestion) {
        // Add validation logic if needed
        return quizQuestionRepository.save(newQuestion);
    }

    public List<QuizQuestionEntity> getAllQuestions() {
        return quizQuestionRepository.findAll();
    }

    public QuizQuestionEntity getQuestionById(int qid) {
        return quizQuestionRepository.findById(qid).orElse(null);
    }

    public QuizQuestionEntity updateQuestion(int qid, QuizQuestionEntity updatedQuestion) {
        try {
            QuizQuestionEntity existingQuestion = quizQuestionRepository.findById(qid).orElseThrow(
                    () -> new NoSuchElementException("Question with ID " + qid + " does not exist!")
            );

            existingQuestion.setTitle(updatedQuestion.getTitle());
            existingQuestion.setQuestion(updatedQuestion.getQuestion());
            existingQuestion.setOptionA(updatedQuestion.getOptionA());
            existingQuestion.setOptionB(updatedQuestion.getOptionB());
            existingQuestion.setOptionC(updatedQuestion.getOptionC());
            existingQuestion.setOptionD(updatedQuestion.getOptionD());
            existingQuestion.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

            return quizQuestionRepository.save(existingQuestion);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Question with ID " + qid + " does not exist!");
        }
    }

    public String deleteQuestion(int qid) {
        quizQuestionRepository.deleteById(qid);
        return "Question with ID " + qid + " has been deleted.";
    }
}
