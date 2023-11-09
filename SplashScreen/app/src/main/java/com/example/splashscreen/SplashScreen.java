package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent it=new Intent(SplashScreen.this,MainActivity.class);
        Handler hm=new Handler();
        hm.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(it);
                finish();//kills the current activity in stack
                finishAffinity();//finish all the activity in stack
            }
        },4000);
       // startActivity(it);


    }
}