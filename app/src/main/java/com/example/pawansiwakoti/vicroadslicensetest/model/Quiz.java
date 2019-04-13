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

}
