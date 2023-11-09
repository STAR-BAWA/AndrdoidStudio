package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText ed1;
    EditText edp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button2);
        ed1=findViewById(R.id.editTextTextPersonName2);
        edp=findViewById(R.id.editTextTextPassword3);
      //  String name=ed1.toString();
       // int pwd=Integer.parseInt(edp.toString());


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=ed1.getText().toString();
                String pwd=edp.getText().toString();

                if(name.equals("admin") || pwd.equals("1234"))
                {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}