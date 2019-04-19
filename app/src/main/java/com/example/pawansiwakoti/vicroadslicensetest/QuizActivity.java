package com.example.pawansiwakoti.vicroadslicensetest;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pawansiwakoti.vicroadslicensetest.repository.AppRepository;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuizCount;
    private AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuizCount = findViewById(R.id.txtQuizCount);

        appRepository = AppRepository.getInstance(this);
        appRepository.getQuizCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvQuizCount.setText(String.valueOf(integer));
            }
        });
    }
}