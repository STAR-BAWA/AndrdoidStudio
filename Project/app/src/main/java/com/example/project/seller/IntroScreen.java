package com.example.project.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class IntroScreen extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(IntroScreen.this, sellerRegistration.class));
//            }
//        });

    }
}