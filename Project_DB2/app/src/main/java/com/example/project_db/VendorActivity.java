package com.example.project_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class VendorActivity extends AppCompatActivity {

    EditText edname,edphoneno,edid,edpwd,eddob,eddor,edimg,edcity,email;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        edname=findViewById(R.id.editTextName);
        edphoneno=findViewById(R.id.editTextPhno);
        edid=findViewById(R.id.editTextUID);
        edpwd=findViewById(R.id.editTextPassword);
        eddob=findViewById(R.id.editTextDate);
        eddor=findViewById(R.id.editTextRegistration);
        edimg=findViewById(R.id.editTextimg);
        edcity=findViewById(R.id.editTextCity);
        email=findViewById(R.id.editTextTextEmailAddress);
        b1=findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(),Vendor_activity2.class);
                it.putExtra("uname",edname.getText().toString());
                it.putExtra("uphno",edphoneno.getText().toString());
                it.putExtra("uid",edid.getText().toString());
                it.putExtra("upwd",edpwd.toString());
                it.putExtra("u_dob",eddob.getText().toString());
                it.putExtra("u_dor",eddor.getText().toString());
                it.putExtra("u_img",edimg.getText().toString());
                it.putExtra("u_city",edcity.getText().toString());
                it.putExtra("mail",email.getText().toString());
                startActivity(it);
                Toast.makeText(VendorActivity.this, "button clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }
}