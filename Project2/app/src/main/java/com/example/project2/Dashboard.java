package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Dashboard extends AppCompatActivity {

    WebView webView;
    String data = "https://console.firebase.google.com/project/project2-b43a8/analytics";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        webView=findViewById(R.id.webView);

        webView.loadUrl(data);

        finish();
    }
}