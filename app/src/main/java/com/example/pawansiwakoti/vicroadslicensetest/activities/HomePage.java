package com.example.pawansiwakoti.vicroadslicensetest.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pawansiwakoti.vicroadslicensetest.R;
import com.example.pawansiwakoti.vicroadslicensetest.fragments.FeedbackFragment;
//import com.example.pawansiwakoti.vicroadslicensetest.R;
//import  android.support.design.widget.NavigationView;

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

        NavigationView mDrawer = (NavigationView) findViewById(R.id.Navigation);
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


        switch (menuItem.getItemId()) {
            case R.id.article:
                Article();
                break;


            case R.id.video:
                video();
                break;
            case R.id.startQuiz:
                startQuiz();
                break;
            case R.id.emailus:
                Email();
                break;
            case R.id.feedback:
               feedback();
                break;
            default:


        }
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
         //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.LLayout,myfragment ).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mdrawerLayout.closeDrawers();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            selectItemDrawer(menuItem);
            return true;
        });
    }


    private void Article() {
        startActivity(new Intent(this, PrepareFortest.class));
    }

    private void video() {
        startActivity(new Intent(this, Video.class));
    }

    private void startQuiz() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void feedback() {
        Intent intent = GeneralActivity.getIntent(this, FeedbackFragment.class,
                FeedbackFragment.class.getSimpleName(), "Feedback");
        startActivity(intent);
    }
    private void Email(){
        startActivity(new Intent(this, Email.class));
    }
}
