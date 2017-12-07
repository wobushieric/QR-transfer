package com.dai.eric.qrtransfer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class OnlineBankingActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_banking);

        this.myWebView = new WebView(this);
        myWebView.loadUrl(getIntent().getStringExtra("link"));
    }
}
