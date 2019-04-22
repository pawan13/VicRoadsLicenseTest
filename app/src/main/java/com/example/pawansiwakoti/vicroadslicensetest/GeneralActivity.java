package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class GeneralActivity extends BaseActivity {

    public static final String ARG_FRAGMENT_CLASSNAME = "arg_fragment_classname";
    public static final String ARG_FRAGMENT_TAGNAME = "arg_fragment_tagname";
    public static final String ARG_ACTIVITY_TITLE = "arg_activity_title";

    public static Intent getIntent(Context context, Class<? extends Fragment> fragment, String fragmentTag, String activityTitle) {
        Intent intent = new Intent(context, GeneralActivity.class);
        intent.putExtra(ARG_FRAGMENT_CLASSNAME, fragment.getName());
        intent.putExtra(ARG_FRAGMENT_TAGNAME, fragmentTag);
        intent.putExtra(ARG_ACTIVITY_TITLE, activityTitle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        FrameLayout fragmentContainer = findViewById(R.id.fragment_container);

        Intent intent = getIntent();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)  {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (!StringUtils.isNullOrEmpty(intent.getStringExtra(ARG_ACTIVITY_TITLE))) {
                actionBar.setTitle(intent.getStringExtra(ARG_ACTIVITY_TITLE));
            }
        }

        Fragment fragment = Fragment.instantiate(this, intent.getStringExtra(ARG_FRAGMENT_CLASSNAME), intentToFragmentArguments(intent));
        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), fragment).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(ARG_ACTIVITY_TITLE, getSupportActionBar().getTitle().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}