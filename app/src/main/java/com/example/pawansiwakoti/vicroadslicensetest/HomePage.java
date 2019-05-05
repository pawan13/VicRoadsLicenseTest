package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
        NavigationView mDrawer = (NavigationView)findViewById(R.id.Navigation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(mDrawer);


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
    public void selectItemDrawer(MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.article:
                 Article();
                    break;
            case R.id.video:
                 video();
                break;
            case R.id.startQuiz:
                   startQuiz();
                break;
            case R.id.feedback:
                  feedback();
                break;
                default:

        }
         try {

         }
         catch (Exception e){
             e.printStackTrace();
         }
       // FragmentManager fragmentManager = getSupportFragmentManager();
        // fragmentManager.beginTransaction().replace(R.id.article ).commit();
         menuItem.setChecked(true);
         setTitle(menuItem.getTitle());
         mdrawerLayout.closeDrawers();

    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            selectItemDrawer(menuItem);
            return true;
        });
    }
    private void Article(){
        startActivity(new Intent(this, PrepareFortest.class));
    }
    private void video(){
        startActivity(new Intent(this, Video.class));
    }
    private void startQuiz(){
        startActivity(new Intent(this, MainActivity.class));
    }
    private void feedback(){
        startActivity(new Intent(this, FeedBack.class));
    }
}