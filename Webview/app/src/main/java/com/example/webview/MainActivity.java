package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    WebViewClient client;
    WebSettings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=findViewById(R.id.Webview);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        String data = "https://console.firebase.google.com/project/project2-b43a8/analytics";
//        String data="String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";  "

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
            }
        });

//        webView.getSettings().setJavaScriptEnabled(true);
webView.getSettings().setBuiltInZoomControls(true);
webView.getSettings().setDisplayZoomControls(false);
webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//                webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);


//        webView.setWebViewClient(new WebViewClient(webView.loadUrl());
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);

   webView.loadUrl(data);
//        webView.setWebViewClient(new WebViewClient());
    }
}