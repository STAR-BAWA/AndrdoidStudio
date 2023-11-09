package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_profile extends AppCompatActivity {

    EditText password,phonenumber;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        phonenumber=findViewById(R.id.user_number);
        password=findViewById(R.id.user_profile_pwd);
        

        b1=findViewById(R.id.user_profile_button);
        
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        if(phonenumber.getText().toString().length()!=10 || password.getText().toString().isEmpty()){
            Toast.makeText(v.getContext(), "Error In details", Toast.LENGTH_SHORT).show();
        }
        else{

            DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("User_Side").child(phonenumber.getText().toString());
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()) {
                        String phno = snapshot.child("Phone_Number").getValue(String.class);
                        String pwd = snapshot.child("password").getValue(String.class);
                        String Name = snapshot.child("Name").getValue(String.class);

                        String email = snapshot.child("email").getValue(String.class);
                        String Address = snapshot.child("Address").getValue(String.class);
                        if (phonenumber.getText().toString().equals(phno) && password.getText().toString().equals(pwd)) {
                            Intent it = new Intent(user_profile.this, user_profile2.class);
                            it.putExtra("Name", Name);
                            it.putExtra("email", email);
                            it.putExtra("Address", Address);
                            startActivity(it);

                        } else {
                            Toast.makeText(v.getContext(), "no user exist", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
            }
        });
        
       
    }
}