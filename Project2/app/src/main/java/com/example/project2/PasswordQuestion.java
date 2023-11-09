package com.example.project2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.project2.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.Change;

import java.util.Iterator;
import java.util.Locale;

public class PasswordQuestion extends AppCompatActivity {
    Button verify,ChangePassword;
    TextView Detail;
    EditText edques1,edques2,edques3;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_question);

        Intent it=getIntent();
        String number=it.getStringExtra("phno");
//        Toast.makeText(this, "login sucessfull", Toast.LENGTH_SHORT).show();
        DatabaseReference dbr=FirebaseDatabase.getInstance().getReference();
        verify=findViewById(R.id.buttonQuestionCheck);
        edques1=findViewById(R.id.editTextQuestion1Security);
        edques2=findViewById(R.id.editTextQuestion2Security);
        edques3=findViewById(R.id.editTextQuestion3Security);
        Detail=findViewById(R.id.textViewDetails);
        ChangePassword=(Button) findViewById(R.id.buttonChange);


                verify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            progressDialog=new ProgressDialog(v.getContext());
                            progressDialog.setTitle("Fetching password");
                            progressDialog.setMessage("Fetching password please wait");
                            progressDialog.setCanceledOnTouchOutside(false);

                           String ques1F=edques1.getText().toString().trim();
                           String ques2F=edques2.getText().toString();
                           String ques3F=edques3.getText().toString();
                           if(ques1F.isEmpty() || ques2F.isEmpty() || ques3F.isEmpty())
                           {
                               Toast.makeText(PasswordQuestion.this, "Empty ", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               progressDialog.show();
                               Toast.makeText(PasswordQuestion.this, number, Toast.LENGTH_SHORT).show();
                               DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vendor1").child(number);
                               reference.addValueEventListener(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot snapshot) {

                                       if (snapshot.exists()) {
                                           String ques1V = snapshot.child("ques1").getValue(String.class).trim();
                                           String ques2V = snapshot.child("ques2").getValue(String.class).trim();
                                           String ques3V = snapshot.child("ques3").getValue(String.class).trim();
                                           if (ques1F.equals(ques1V) && ques2F.equals(ques2V) && ques3F.equals(ques3V)) {
//                                        Toast.makeText(PasswordQuestion.this, "success", Toast.LENGTH_SHORT).show();
                                               progressDialog.dismiss();
                                               String pwd = snapshot.child("password").getValue(String.class);
                                               Detail.setText(pwd);
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

        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String ques1F=edques1.getText().toString().trim();
                           String ques2F=edques2.getText().toString();
                           String ques3F=edques3.getText().toString();

                           if(ques1F.isEmpty() || ques2F.isEmpty() || ques3F.isEmpty())
                           {
                               Toast.makeText(PasswordQuestion.this, "Empty ", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Vendor1").child(number);
                               reference.addValueEventListener(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                                       if (snapshot.exists()) {
                                           if (snapshot.exists()) {
                                               String ques1V = snapshot.child("ques1").getValue(String.class).trim();
                                               String ques2V = snapshot.child("ques2").getValue(String.class).trim();
                                               String ques3V = snapshot.child("ques3").getValue(String.class).trim();
                                               if ((ques1F.equals(ques1V) && ques2F.equals(ques2V) && ques3F.equals(ques3V))) {
//                                        Toast.makeText(PasswordQuestion.this, "success", Toast.LENGTH_SHORT).show();
                                                   startActivity(new Intent(PasswordQuestion.this, com.example.project2.ChangePassword.class));
                                               }
                                           }

                                       } else {
                                           Toast.makeText(PasswordQuestion.this, "not possible", Toast.LENGTH_SHORT).show();
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

