package com.example.pawansiwakoti.vicroadslicensetest.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pawansiwakoti.vicroadslicensetest.R;

public class QuizHistory extends AppCompatActivity {
 TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_history);
        textView = findViewById(R.id.QuizHistory);
        onStart();
    }
    void getHistory() {
        SharedPreferences settings = getSharedPreferences("MyQuizHistory", 0);
        String History = settings.getString("History", "");
        textView.setText(History);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getHistory();
    }
}

