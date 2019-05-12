package com.example.pawansiwakoti.vicroadslicensetest.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public Bundle intentToFragmentArguments(Intent intent) {
        Bundle arguments = new Bundle();
        if (intent == null) return arguments;

        Uri data = intent.getData();
        if (data != null) arguments.putParcelable("_uri", data);

        Bundle extras = intent.getExtras();
        if (extras != null) arguments.putAll(extras);

        return arguments;
    }
}