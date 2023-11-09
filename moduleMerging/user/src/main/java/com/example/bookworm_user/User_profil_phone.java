package com.example.bookworm_user;

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
import com.google.firestore.bundle.BundledQueryOrBuilder;

public class User_profil_phone extends AppCompatActivity {

    Button b1;
    EditText phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil_phone);
//        String num=phoneNumber.getText().toString();
        phoneNumber=findViewById(R.id.editTextPhoneSeller);
        b1=findViewById(R.id.buttonGetDetails);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(phoneNumber.getText().toString());
               reference.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists())
                       {
                           String num=snapshot.child("Phone").getValue(String.class);
                           String name=snapshot.child("Name").getValue(String.class);
                           String email=snapshot.child("Email").getValue(String.class);

                           Toast.makeText(User_profil_phone.this, "Sure success", Toast.LENGTH_SHORT).show();
                           Intent it=new Intent(User_profil_phone.this,profile_user.class);
                           it.putExtra("Number",num);
                           it.putExtra("Name",name);
                           it.putExtra("Email",email);
                           Toast.makeText(User_profil_phone.this, "Sucess2", Toast.LENGTH_SHORT).show();
                           startActivity(it);
                       }
                       else{
                           Toast.makeText(User_profil_phone.this, "No data found", Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }
        });

    }
}