package com.example.pawansiwakoti.vicroadslicensetest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pawansiwakoti.vicroadslicensetest.utils.Questions;
import com.example.pawansiwakoti.vicroadslicensetest.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnQuiz, btnQuizTest,Quit;

    private Questions mQuestions = new Questions();

    private int mScore = 0;
    private int mQuestionsLength = mQuestions.mQuestions.length;

    Random r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();



        btnQuiz = findViewById(R.id.btnQuiz);
        btnQuizTest = findViewById(R.id.btnQuizTest);
        btnQuizTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra(QuizActivity.ARG_IS_PRACTICE, false);
            startActivity(intent);
        });
        btnQuiz.setOnClickListener(v -> {
            // Start quiz in test mode
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra(QuizActivity.ARG_IS_PRACTICE, true);
            startActivity(intent);
        });

        Quit = (Button) findViewById(R.id.Quit);


        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Quit = new Intent(getApplicationContext(), HomePage.class);
                startActivity(Quit);
                finish();
            }
        });

    }
}