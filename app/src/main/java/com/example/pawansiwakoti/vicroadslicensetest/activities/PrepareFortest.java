package com.example.pawansiwakoti.vicroadslicensetest.activities;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.pawansiwakoti.vicroadslicensetest.R;

public class PrepareFortest extends Activity {

    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_fortest);
       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.vicroads.vic.gov.au/licences/your-ls/your-learner-handbooks");
        //linked with the website
        webView.setWebViewClient(new WebViewClient());
        //to prevent to open the new window

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();

        }else {
        super.onBackPressed();
    }*/
       //This is commented because of copyright issue
    }
}
