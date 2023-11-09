package com.example.splashscreendatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent it=new Intent(SplashScreen.this,MainActivity.class);
        Handler hm=new Handler();
        pb=findViewById(R.id.progressBar);
        hm.postDelayed(new Runnable() {
            @Override
            public void run() {
                //pb=findViewById(R.id.progressBar);
                startActivity(it);
                finish();//clears this activity from the stack
            }
        },4000);
    }
}