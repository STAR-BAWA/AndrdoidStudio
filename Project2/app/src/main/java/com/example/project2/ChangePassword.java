package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {

    EditText password1,password2;
    EditText phoneNumber;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password1=findViewById(R.id.editTextChangePassword1);
        password2=findViewById(R.id.editTextChangePassword2);
        change=findViewById(R.id.buttonChangePass);

        phoneNumber=findViewById(R.id.editTextChangePassword3);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1=password1.getText().toString();
                String pass2=password2.getText().toString();
                String phone=phoneNumber.getText().toString();

                if(pass1.isEmpty() || pass2.isEmpty() || phone.isEmpty())
                {
                    Toast.makeText(ChangePassword.this, "Some details are missing", Toast.LENGTH_SHORT).show();
                }
                if(pass1.equals(pass2) && pass1.length() !=1) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vendor1");
                    reference.child(phone).child("password").setValue(pass1);
                    Toast.makeText(ChangePassword.this, "Sucess", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}