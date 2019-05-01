package com.example.pawansiwakoti.vicroadslicensetest;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pawansiwakoti.vicroadslicensetest.repository.AppRepository;

//UPDATE YOUR CHANGELOG FILE - Henry

public class SplashScreen extends AppCompatActivity {

    private AppRepository appRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appRepository = AppRepository.getInstance(this);

        appRepository.getQuizCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer count) {
                if (count != null && count > 0) {
                    gotoHomeScreen();
                } else {
                    getDataFromApiFirstTime();
                }
            }
        });

        /*Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(5000);
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                        finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();*/
    }

    private void getDataFromApiFirstTime() {
        if (hasActiveInternetConnection(this)) {
            // Fetch data and save to db
            appRepository.initiateDataFetching(false);
        }
    }

    private void gotoHomeScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, HomePage.class));
                finish();
            }
        }, 1000);
    }

    private boolean hasActiveInternetConnection(Context context) {
        return getNetworkInfo(context).isConnected();
    }

    private NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
