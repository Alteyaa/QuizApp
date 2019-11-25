package com.quizapp.model;

import java.util.Date;

public class ShortQuizResult {
    private int id;
    private int questionsAmount;
    private int correctAnswers;
    private Date createAt;

    public ShortQuizResult(int id, int questionsAmount, int correctAnswers, Date createAt) {
        this.id = id;
        this.questionsAmount = questionsAmount;
        this.correctAnswers = correctAnswers;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionsAmount() {
        return questionsAmount;
    }

    public void setQuestionsAmount(int questionsAmount) {
        this.questionsAmount = questionsAmount;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
