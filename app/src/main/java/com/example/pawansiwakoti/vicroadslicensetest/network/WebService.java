package com.example.pawansiwakoti.vicroadslicensetest.network;

import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    @GET("/api/quiz")
    Call<List<Quiz>> getAllQuizes();
}
