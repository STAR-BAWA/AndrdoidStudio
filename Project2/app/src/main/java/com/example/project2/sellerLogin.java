package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.project2.ui.login.LoginActivity;
//import com.example.project2.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sellerLogin extends AppCompatActivity {


    EditText edmail;
    ProgressDialog pdg;
    TextInputEditText edpass;
    TextView Forgot;
    TextView textforgot;
    Button loginVendor;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);


        edmail=findViewById(R.id.editTextmail);
        edpass=findViewById(R.id.editTextInputLayout);
        textforgot=findViewById(R.id.textViewForgot);
        Forgot=findViewById(R.id.forgotpassword);
        loginVendor=findViewById(R.id.buttonlogin);
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sellerLogin.this, "Sucess", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),sellerRegistration.class));

            }
        });
        
        loginVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String mail=edmail.getText().toString();
              String pass=edpass.getText().toString();

              if(mail.isEmpty() && pass.isEmpty())
              {
                  Toast.makeText(sellerLogin.this, "Please enter login details", Toast.LENGTH_SHORT).show();
              }
              else {
                  FirebaseAuth auth = FirebaseAuth.getInstance();
                  auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {

//                        Toast.makeText(sellerLogin.this, "Hello", Toast.LENGTH_SHORT).show();
                          if (task.isSuccessful()) {
                              Toast.makeText(sellerLogin.this, "Authenticated", Toast.LENGTH_SHORT).show();
                              Intent it = new Intent(sellerLogin.this, HomePageVendor.class);

                              startActivity(it);
                              edmail.setText("");
                              edpass.setText("");
                          }
                          else{
                              Toast.makeText(sellerLogin.this, "No user found ", Toast.LENGTH_SHORT).show();
                          }
                      }
                  }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                      @Override
                      public void onSuccess(AuthResult authResult) {

                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          Toast.makeText(sellerLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });
              }
            }
        });

        textforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sellerLogin.this,forgotpassword.class));
            }
        });

    }
}