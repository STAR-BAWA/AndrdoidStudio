package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1=findViewById(R.id.Button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent it =new Intent(MainActivity.this,SecondActivity.class);
            it.putExtra("name","Star Bawa");
            it.putExtra("roll no",10);
            //as shown above there are 2 components
            // a key and a value   the above are passed from in form of key and value
            startActivity(it);
            }
            //intent passing in java is known as bundle passing
            //since we pass information in the intent
        });
    }
}