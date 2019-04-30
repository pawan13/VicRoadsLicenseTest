package com.example.pawansiwakoti.vicroadslicensetest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.pawansiwakoti.vicroadslicensetest.model.Answers;
import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;
import com.example.pawansiwakoti.vicroadslicensetest.repository.AppRepository;

import java.util.List;

/**
 * ViewModel class to manage view data for general activity
 */
public class GeneralActivityViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    public GeneralActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Answers>> getAllAnswers() {
        return mRepository.getAllAnswers();
    }

    public LiveData<List<Answers>> getAnswersBySession(String sessionId) {
        return mRepository.getAnswersBySession(sessionId);
    }


    public LiveData<List<Quiz>> getFrequentlyWrongedQuiz() {
        return mRepository.getFrequentlyWrongedQuiz();
    }
}
