package com.example.pawansiwakoti.vicroadslicensetest;

import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonMethods {

    /**
     * Function to find one particular quiz by its id
     * @param quizList
     * @param id
     * @return a quiz found byid
     * @example filterQuizById(quizzes, "abcdefgh")
     */
    public static Quiz filterQuizById(List<Quiz> quizList, String id) {
        if (quizList == null || quizList.size() < 1) return null;
        for (int i = 0; i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            if (id.equalsIgnoreCase(quiz.getId())) return quiz;
        }
        return null;
    }

    /**
     * Function to generate given number of random quizzes
     * @param allQuizzes
     * @param maxCount
     * @return randomized quiz list
     * @example getRandomXQuizzes()
     */
    public static List<Quiz> getRandomXQuizzes(List<Quiz> allQuizzes, int maxCount) {
        List<Quiz> result = new ArrayList<Quiz>();
        Random random = new Random();
        if (allQuizzes == null || allQuizzes.size() == 0) {
            return result;
        }
        else {
            int randomNum;
            while (true) {
                randomNum = random.nextInt(allQuizzes.size());
                if (!result.contains(allQuizzes.get(randomNum))) {
                    result.add(allQuizzes.get(randomNum));
                }
                if (result.size() >= maxCount) break;
            }
        }
        return result;
    }
}
