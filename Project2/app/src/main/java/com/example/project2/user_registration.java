package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class user_registration extends AppCompatActivity {

     private EditText edname, edpass, edphno, edaddress, edemail, ques1, ques2, ques3;
    Button register;
    String uid;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        
        edname = (EditText) findViewById(R.id.VendorNameid);
        edpass = (EditText) findViewById(R.id.vendorPassId);
        edphno = (EditText) findViewById(R.id.VendorPhNumber);
        edaddress = (EditText) findViewById(R.id.editTextAddress);
        edemail = (EditText) findViewById(R.id.vendormail);
        ques1 = (EditText) findViewById(R.id.editTextQuestion1);
        ques2 = (EditText) findViewById(R.id.editTextQuestion2);
        ques3 = (EditText) findViewById(R.id.editTextQuestion3);
        register = findViewById(R.id.buttonRegister);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 DatabaseReference rdb = FirebaseDatabase.getInstance().getReference("User_Side");
//
                String email=edemail.getText().toString();
                HashMap<String, Object> map = new HashMap<String, Object>();
                String name=edname.getText().toString();
                String pwd=edpass.getText().toString();
                String phone=edphno.getText().toString();
                String address=edaddress.getText().toString();

                FirebaseAuth auth=FirebaseAuth.getInstance();
                String Ques1=ques1.getText().toString();
                String Ques2=ques2.getText().toString();
                String Ques3=ques3.getText().toString();
                Pattern pattern= Pattern.compile("\\b\\S+@(gmail|hotmail|ymail)\\.com\\b");

                Matcher matcher=pattern.matcher(email);
                boolean matchFound=matcher.find();

                if(name.isEmpty() || pwd.isEmpty() || phone.isEmpty() || address.isEmpty() || Ques1.isEmpty() || Ques2.isEmpty() || Ques3.isEmpty() || email.isEmpty() || !matchFound || pwd.length()<6)
                {

                    Toast.makeText(user_registration.this, "Enter all details", Toast.LENGTH_SHORT).show();
                    Toast.makeText(user_registration.this, "Email may be not correct or may be empty", Toast.LENGTH_SHORT).show();
                    Toast.makeText(user_registration.this, "Password may be less than 6 character ", Toast.LENGTH_SHORT).show();
                }
                else {
                    map.put("Name", edname.getText().toString());
                    map.put("password", edpass.getText().toString());
                    map.put("Phone_Number", edphno.getText().toString());
                    map.put("Rating","5");
                    map.put("Address", edaddress.getText().toString());
                    map.put("email", email);
                    map.put("ques1", ques1.getText().toString());
                    map.put("ques2", ques2.getText().toString());
                    map.put("ques3", ques3.getText().toString());

                    //by default i give you 5 rating

                    DatabaseReference rdb_mail = rdb;
                    rdb_mail.child(edphno.getText().toString()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(user_registration.this, "You are registered sucessfully", Toast.LENGTH_SHORT).show();
                            FirebaseAuth auth=FirebaseAuth.getInstance();
                            auth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                      if (task.isSuccessful()) {
                                          Toast.makeText(user_registration.this, "Sucessful Authentication", Toast.LENGTH_SHORT).show();

                                      }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(user_registration.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                            startActivity(new Intent(user_registration.this, user_home.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(user_registration.this, "registeration not possible", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
    }
