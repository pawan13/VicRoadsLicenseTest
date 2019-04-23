package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button answer1, answer2, answer3;
    TextView score, question;
    Button QuizHistory,Quit;

    private Button btnQuiz, btnQuizTest;

    private Questions mQuestions = new Questions();
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsLength = mQuestions.mQuestions.length;

    Random r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();

        answer1 = (Button)findViewById(R.id.button);
        //answer2 = (Button)findViewById(R.id.button1);
        answer3 = (Button)findViewById(R.id.button2);

        btnQuiz = findViewById(R.id.btnQuiz);
        btnQuizTest = findViewById(R.id.btnQuizTest);
        btnQuizTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra(QuizActivity.ARG_IS_PRACTICE, true);
            startActivity(intent);
        });
        btnQuiz.setOnClickListener(v -> {
            // Start quiz in test mode
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra(QuizActivity.ARG_IS_PRACTICE, false);
            startActivity(intent);
        });

        score = (TextView) findViewById(R.id.textView2);
        question = (TextView) findViewById(R.id.textView);

        QuizHistory = (Button)findViewById(R.id.QuizHistory);
        Quit = (Button)findViewById(R.id.Quit);

        QuizHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("MyQuizHistory",0);
                SharedPreferences.Editor editor = settings.edit();
                String History = score.getText().toString();
                editor.putString("History",History);
                editor.apply();

                Intent QuizHistory = new Intent (getApplicationContext(), QuizHistory.class);
                startActivity(QuizHistory);
                finish();
            }
        });
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Quit = new Intent(getApplicationContext(), HomePage.class);
                startActivity(Quit);
                finish();
            }
        });

        updateQuestion(r.nextInt (mQuestionsLength));


        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score:" + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    mScore = mScore;
                    score.setText("score:" + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                }

            }
        });
        /*answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score:" + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    mScore = mScore;
                    score.setText("score:" + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                }
            }
        });*/
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer3.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score:" + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    mScore = mScore;
                    score.setText("score:" + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                }

            }
        });

    }
    private void gameover() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("GameOver! Your score is " + mScore + "points.")
                .setCancelable(false)
                .setPositiveButton("NEW Quiz",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), HomePage.class));

                            }

                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }

                        });
    }
    private void updateQuestion(int num) {
        question.setText(mQuestions.getQuestion(num));
        answer1.setText(mQuestions.getChoice1(num));
        //answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));


        mAnswer = mQuestions.getmCorrectAnswers(num);

    }

}