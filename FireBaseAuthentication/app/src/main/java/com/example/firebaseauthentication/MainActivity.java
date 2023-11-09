package com.example.firebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;

public class MainActivity extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    Button b1,b2;
    FirebaseAuth auth;
    FirebaseAuthEmailException fe;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        ed1 = (EditText) findViewById(R.id.editTextTextPersonName);
        ed2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        b1 = findViewById(R.id.button);
//        String email;
//        String password;


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ed1.getText().toString();
                password = ed2.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                } else
                    regis(email,password);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all the details", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void regis(String email,String password)
    {
       auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful())
               {
                   Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();  
               }
               else
                   Toast.makeText(MainActivity.this, "Data cannot be added ", Toast.LENGTH_SHORT).show();

           }
       });
    }

//    public void login(String email,String password)
//    {
//        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful())
//                {
//                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

}