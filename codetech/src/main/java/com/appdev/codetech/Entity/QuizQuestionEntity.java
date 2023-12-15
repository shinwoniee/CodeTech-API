package com.appdev.codetech.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblquizquestion")
public class QuizQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qid;

    @Column(name = "title")
    private String title;

    @Column(name = "question")
    private String question;

    @Column(name = "A")
    private String optionA;

    @Column(name = "B")
    private String optionB;

    @Column(name = "C")
    private String optionC;

    @Column(name = "D")
    private String optionD;

    @Column(name = "answer")
    private String correctAnswer;

    public QuizQuestionEntity() {
    }

    public QuizQuestionEntity(int qid, String title, String question, String optionA, String optionB, String optionC,
            String optionD, String correctAnswer) {
        this.qid = qid;
        this.title = title;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
