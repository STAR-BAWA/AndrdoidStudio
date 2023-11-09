package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //fetching the intent content
        TextView txv2;
        //STores the intents  in form of a stack and getIntent get the address of the last
        //main intent
        Intent fetchIntent;
        fetchIntent=getIntent();
       String name= fetchIntent.getStringExtra("name");//for fetching string data
        int  number=Integer.parseInt(fetchIntent.getStringExtra("roll no"));
//        int rollno=fetchIntent.getIntExtra("RollNo");
        txv2=findViewById(R.id.textView);
        //boolean isMarried;
//        getSupportActionBar().setTitle(number);
      //  else  isMarried=true;
        txv2.setText(name+" "+number);
        //System.out.println(name+""+number+"");
    }
}