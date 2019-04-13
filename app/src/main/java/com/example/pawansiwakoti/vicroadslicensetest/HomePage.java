package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button article, Quiz, Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
         article = (Button)findViewById(R.id.button3);
         Quiz = (Button)findViewById(R.id.button4);
         Result = (Button)findViewById(R.id.button5);

         article.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent article = new Intent(HomePage.this, PrepareFortest.class);
                 startActivity(article);

             }
         });

        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quiz= new Intent(HomePage.this, MainActivity.class);
                startActivity(quiz);

            }
        });
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result= new Intent(HomePage.this, Result.class);
                startActivity(result);
            }
        });
    }
}
