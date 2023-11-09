package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class forgotpassword extends AppCompatActivity {

    EditText mailf;
    EditText num_fetch;
    Button fetchPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        mailf=findViewById(R.id.EmailForgotPassword);
        fetchPassword=findViewById(R.id.buttonVerifyEmail);
        num_fetch=findViewById(R.id.NumberFetch);
        fetchPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(forgotpassword.this, "success", Toast.LENGTH_SHORT).show();
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Vendor1");

                ref.child(num_fetch.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String mail=snapshot.child("email").getValue().toString();
                        if(mail.equals(mailf.getText().toString())) {

                            Toast.makeText(forgotpassword.this, mail, Toast.LENGTH_SHORT).show();
                            Intent it=new Intent(forgotpassword.this,PasswordQuestion.class);
                            it.putExtra("phno",num_fetch.getText().toString());
                            startActivity(it);
                        }
                        else
                            Toast.makeText(forgotpassword.this,"Not Sucessful", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}
