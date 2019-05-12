package com.example.pawansiwakoti.vicroadslicensetest.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pawansiwakoti.vicroadslicensetest.R;
import com.example.pawansiwakoti.vicroadslicensetest.activities.MainActivity;
import com.example.pawansiwakoti.vicroadslicensetest.activities.SplashScreen;
import com.example.pawansiwakoti.vicroadslicensetest.databinding.ActivityResultBinding;

public class Result extends AppCompatActivity implements View.OnClickListener{
    public static final String ARG_TOTAL = "arg_total";
    public static final String ARG_CORRECT = "arg_correct";
    public static final String ARG_SKIP = "arg_skip";
    public static final double PASS_PERCENTAGE = 50;

    private ActivityResultBinding binding;
    private int total;
    private int correct;
    private int skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_result);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        setupActionbar();
        Intent intent = getIntent();
        if (intent != null)
        {
            total = intent.getIntExtra(ARG_TOTAL,0);
            correct = intent.getIntExtra(ARG_CORRECT,0);
            skip = intent.getIntExtra(ARG_SKIP,0);
            calculateAndDisplayingResult();
        }
        binding.buttonGoHome.setOnClickListener(this);
        binding.buttonTryAgain.setOnClickListener(this);
    }
    private void calculateAndDisplayingResult() {
        double acquiredPercentage = total == 0?0: (correct/total)*100;
        changeUIFor(acquiredPercentage>PASS_PERCENTAGE);
    }
    private void changeUIFor(boolean pass){
        int color = getResources().getColor(pass ? R.color.colorPrimary: R.color.colorPrimary);
        Drawable drawable = ContextCompat.getDrawable(this, pass?R.drawable.ic_success : R.drawable.ic_exclamation);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable,color);
        binding.imageResult.setImageDrawable(drawable);
        binding.textFeedback.setText(pass ? "Nice!": "Opps!");
        binding.textPoints.setText(String.format("%id/%id, correct, total"));
        binding.textPoints.setTextColor(color);
        binding.textFeedback.setTextColor(color);


    }
    private void setupActionbar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null)
            return;
        actionBar.setTitle("Result");
    }


    @Override
    public void onClick(View v){
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_go_home:
                intent = new Intent(this, SplashScreen.class);
                break;
            case R.id.button_try_again:
                intent = new Intent(this, MainActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }
    }

