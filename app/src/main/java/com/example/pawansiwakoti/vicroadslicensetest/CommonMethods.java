package com.example.pawansiwakoti.vicroadslicensetest;

import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;

import java.util.List;

public class CommonMethods {

    public static Quiz filterQuizById(List<Quiz> quizList, String id) {
        if (quizList == null || quizList.size() < 1) return null;
        for (int i = 0; i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            if (id.equalsIgnoreCase(quiz.getId())) return quiz;
        }
        return null;
    }
}
