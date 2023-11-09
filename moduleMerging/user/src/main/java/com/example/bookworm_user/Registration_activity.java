package com.example.bookworm_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Registration_activity extends AppCompatActivity {
    private Button createAccBtn;
    private ProgressDialog loadingBar;
    private EditText input_name,input_email,input_phno,input_password,input_question1,input_question2,input_question3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        createAccBtn=(Button) findViewById(R.id.register_btn);
        input_name=(EditText) findViewById(R.id.register_name_input);
        input_email=(EditText) findViewById(R.id.register_email_input);
        input_phno=(EditText) findViewById(R.id.register_phno_input);
        input_password=(EditText) findViewById(R.id.register_password_input);
        input_question1=(EditText)findViewById(R.id.register_question1_input);
        input_question2=(EditText)findViewById(R.id.register_question2_input);
        input_question3=(EditText)findViewById(R.id.register_question3_input);
        loadingBar= new ProgressDialog(this);
        loadingBar=new ProgressDialog(this);
        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });

    }
    private void CreateAccount()
    {
        String name=input_name.getText().toString();
        String email=input_email.getText().toString();
        String phno=input_phno.getText().toString();
        String pass=input_password.getText().toString();
        String ques1=input_question1.getText().toString();
        String ques2=input_question2.getText().toString();
        String ques3=input_question3.getText().toString();
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please Enter Your Email",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phno))
        {
            Toast.makeText(this,"Please Enter Your Phone Number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(ques1))
        {
            Toast.makeText(this,"Please Enter Your Answer",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(ques2))
        {
            Toast.makeText(this,"Please Enter Your Answer",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(ques3))
        {
            Toast.makeText(this,"Please Enter Your Answer",Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidateEmail(name,email,phno,pass,ques1,ques2,ques3);
        }

    }
    private <DataReference> void ValidateEmail(String name, String email, String phno, String pass, String ques1, String ques2, String ques3)
    {
        final DatabaseReference Rootref;
        Rootref= FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(email.replace('.','a')).exists()))
                {
                    HashMap<String,Object> userdataMap=new HashMap<>();
                    userdataMap.put("Name",name);
                    userdataMap.put("Email",email);
                    userdataMap.put("Phone",phno);
                    userdataMap.put("Password",pass);
                    userdataMap.put("ques1",ques1);
                    userdataMap.put("ques2",ques2);
                    userdataMap.put("ques3",ques3);
                    Rootref.child("Users").child(email).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                              Toast.makeText(Registration_activity.this, "Congratulations, your account has been created", Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();

                                Intent intent=new Intent(Registration_activity.this,Home_Activity.class);
                                startActivity(intent);
                            }
                        else
                            {
                              Toast.makeText(Registration_activity.this,"Network issue",Toast.LENGTH_SHORT).show();
                              loadingBar.dismiss();
                            }

                        }}

                    );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

    }
}