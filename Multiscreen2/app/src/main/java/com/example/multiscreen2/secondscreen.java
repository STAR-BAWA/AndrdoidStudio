package com.example.multiscreen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class secondscreen extends AppCompatActivity {

    TextView txv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondscreen);
        Intent intent=getIntent();
        String message=intent.getStringExtra(MainActivity.msg);
        //gets the value for the key of mainactivity.msg

        txv1=findViewById(R.id.textView);
        txv1.setText(message);
    }
}