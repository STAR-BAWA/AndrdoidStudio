package com.example.newproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv=(WebView)findViewById(R.id.web1);
        wv.setWebViewClient(new WebViewClient());// alots a space to the screen

        wv.loadUrl("https://www.facebook.com/oncountrywork/");// loads the url to the screen
    }
}