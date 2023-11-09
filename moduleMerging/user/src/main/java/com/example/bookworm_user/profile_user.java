package com.example.bookworm_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class profile_user extends AppCompatActivity {



   TextView Name,Email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
Name=findViewById(R.id.Profile_name);
Email=findViewById(R.id.Profile_Email);
phone=findViewById(R.id.Profile_Phone);
        Intent it=getIntent();
        String num=it.getStringExtra("Number").toString();
        Toast.makeText(this, num, Toast.LENGTH_SHORT).show();
        String name=it.getStringExtra("Name");
        String email=it.getStringExtra("Email");
        String Phone=it.getStringExtra("Number");

Name.setText(name);
Email.setText(email);
phone.setText(Phone);
    }
}




