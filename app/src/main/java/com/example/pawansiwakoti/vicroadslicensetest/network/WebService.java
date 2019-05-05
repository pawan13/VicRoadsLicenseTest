package com.example.pawansiwakoti.vicroadslicensetest.network;

import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
//import retrofit2.http.POST
//import retrofit2.http.Body

public interface WebService {
    @GET("/api/quiz")
    Call<List<Quiz>> getAllQuizes();

    @POST("/api/feedback")
    Call<ApiResponse> submitFeedback(@Body FeedbackSchema feedbackSchema);

}
