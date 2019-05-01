package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mdrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  /*  Button article, Quiz, Result;


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
    }*/
    }
}