package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class user_profile2 extends AppCompatActivity {

    TextView t1;
    TextView t2;
    TextView t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile2);

         t1=findViewById(R.id.user_name);
         t2=findViewById(R.id.user_rating);
         t3=findViewById(R.id.user_address);

        Intent it=getIntent();

        t1.setText(it.getStringExtra("Name").toString());
        t2.setText(it.getStringExtra("email").toString());
        t3.setText(it.getStringExtra("Address").toString());

    }
}