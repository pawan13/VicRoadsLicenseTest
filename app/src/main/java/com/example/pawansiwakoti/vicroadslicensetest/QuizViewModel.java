package com.example.pawansiwakoti.vicroadslicensetest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.pawansiwakoti.vicroadslicensetest.model.Answers;
import com.example.pawansiwakoti.vicroadslicensetest.model.Quiz;
import com.example.pawansiwakoti.vicroadslicensetest.repository.AppRepository;

import java.util.HashMap;
import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    private AppRepository mRepository;
    private HashMap<String, Integer> map;
    private MutableLiveData<HashMap<String, Integer>> liveMap;
    private int currentPagerPosition;
    private boolean isPractice;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        map = new HashMap<>();
    }

    LiveData<List<Quiz>> getAllQuizes() {
        //return mRepository.getAllQuizes();
        return mRepository.getAllQuizes();
    }

    void setOption(String id, int answerIndex) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(id, answerIndex);
        liveMap.setValue(map);
    }

    LiveData<HashMap<String, Integer>> getOptionMap() {
        if (liveMap == null) {
            liveMap = new MutableLiveData<>();
        }
        if (map == null) {
            map = new HashMap<>();
        }
        liveMap.setValue(map);
        return liveMap;
    }

    int getCurrentPagerLocation() {
        return currentPagerPosition;
    }

    void setCurrentPagerPosition(int position) {
        currentPagerPosition = position;
    }

    public void saveAnswers(List<Answers> answers) {
        mRepository.saveAnswers(answers);
    }

    public boolean isPractice() {
        return isPractice;
    }

    public void setPractice(boolean practice) {
        isPractice = practice;
    }

}