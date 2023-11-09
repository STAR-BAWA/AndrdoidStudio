package com.example.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class show_data extends AppCompatActivity {

    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        Intent it=getIntent();
        String name=(String)it.getStringExtra("name");
        String ID=(String)it.getStringExtra("ID");
        String phno=(String)it.getStringExtra("phno");

        System.out.println(name);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);


        t1.setText(name.toString());
        t2.setText(ID.toString());
        t3.setText(phno.toString());

        t1.setText("THis is executed");

    }
}