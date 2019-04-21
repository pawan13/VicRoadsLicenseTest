package com.example.pawansiwakoti.vicroadslicensetest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "answers")
public class Answers {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "session_id")
    private String sessionId;

    @ColumnInfo(name = "quiz_id")
    private String quizId;

    private boolean skipped;

    @ColumnInfo(name = "selected_answer")
    private String selectedAnswer;

    @ColumnInfo(name = "right_answer")
    private String rightAnswer;

    @ColumnInfo(name = "correctly_answered")
    private boolean correctlyAnswered;

    @ColumnInfo(name = "created_at")
    private Date createdAt;

    @Ignore
    public Answers() {
    }

    @Ignore
    public Answers(String sessionId, String quizId, boolean skipped, String selectedAnswer, String rightAnswer, boolean correctlyAnswered, Date createdAt) {
        this.sessionId = sessionId;
        this.quizId = quizId;
        this.skipped = skipped;
        this.selectedAnswer = selectedAnswer;
        this.rightAnswer = rightAnswer;
        this.correctlyAnswered = correctlyAnswered;
        this.createdAt = createdAt;
    }

    public Answers(@NonNull int id, String sessionId, String quizId, boolean skipped, String selectedAnswer, String rightAnswer, boolean correctlyAnswered, Date createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.quizId = quizId;
        this.skipped = skipped;
        this.selectedAnswer = selectedAnswer;
        this.rightAnswer = rightAnswer;
        this.correctlyAnswered = correctlyAnswered;
        this.createdAt = createdAt;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public boolean isCorrectlyAnswered() {
        return correctlyAnswered;
    }

    public void setCorrectlyAnswered(boolean correctlyAnswered) {
        this.correctlyAnswered = correctlyAnswered;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}