package com.example.pawansiwakoti.vicroadslicensetest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "quiz")
public class Quiz {
    @PrimaryKey
    @NonNull
    @SerializedName("_id")
    private String id;

    @SerializedName("Question")
    private String question;

    @ColumnInfo(name = "question_image_url")
    @SerializedName("Image")
    private String questionImageUrl;

    @SerializedName("Options")
    private String[] options;

    @SerializedName("Answer")
    private int answer;

    @ColumnInfo(name = "created_at")
    @SerializedName("CreatedAt")
    private Date createdAt;

    @ColumnInfo(name = "modified_at")
    @SerializedName("ModifiedAt")
    private Date modifiedAt;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
