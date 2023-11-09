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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookworm_user.Model.Users;
import com.example.bookworm_user.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class login_activity extends AppCompatActivity {
    Button login_regis_btn;
    private EditText InputEmail,InputPassword;
    private TextView AdminLink,NotAdminLink;
    Button forget_pass;
    private Button Register_In_LoginButton;
    private String parentDbName="Users";
    private ProgressDialog loadingBar;
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        login_regis_btn=(Button) findViewById(R.id.login_button);
        forget_pass=(Button) findViewById(R.id.forget_password_link);
        InputEmail=(EditText)findViewById(R.id.login_email_input);
        InputPassword=(EditText)findViewById(R.id.login_password_input);
        Register_In_LoginButton=(Button)findViewById(R.id.join_btn);
        loadingBar= new ProgressDialog(this);
        AdminLink=(TextView)findViewById(R.id.Admin_btn);
        NotAdminLink=(TextView)findViewById(R.id.not_admin_btn);
        chkBoxRememberMe=(CheckBox)findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        Register_In_LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_activity.this,Registration_activity.class);
                startActivity(intent);
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_regis_btn.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName="Admins";

            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_regis_btn.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName="Admins";
            }
        });
        login_regis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
LoginUser();

//                LoginUser_final(InputEmail.getText().toString(), InputPassword.getText().toString());

            }

//            private void LoginUser_final(String email, String password) {
//
//                FirebaseAuth auth = FirebaseAuth.getInstance();
//                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isComplete()) {
//                            Toast.makeText(login_activity.this, "Success", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(login_activity.this, Home_Activity.class));
//                        }
//                    }
//                });


  //          }

    private void LoginUser() {
String email=InputEmail.getText().toString();
String pass=InputPassword.getText().toString();
if(TextUtils.isEmpty(email))
{
    Toast.makeText(login_activity.this, "Please enter your Email Address", Toast.LENGTH_SHORT).show();
}
else if(TextUtils.isEmpty(pass))
{
    Toast.makeText(login_activity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
}
else
{
    loadingBar.setTitle("Login Account");
    loadingBar.setMessage("Please wait, while we are checking the credentials");
    loadingBar.setCanceledOnTouchOutside(false);
    loadingBar.show();
    Intent intent= new Intent(login_activity.this,Home_Activity.class);
    startActivity(intent);
    AllowAccessToAccount(email,pass);
}
    }
   private void AllowAccessToAccount(String email, String pass)
   {
        if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserEmailKey,email);
            Paper.book().write(Prevalent.UserPasswordKey,pass);
        }



        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(parentDbName).child(email.replace('.','a')).exists())
                {
                    Users usersData =snapshot.child(parentDbName).child(email).getValue(Users.class);
                    if(usersData.getEmail().equals(email))
                    {
                        if(usersData.getPassword().equals(pass))
                        {
                            if(parentDbName.equals("Admins"))
                            {
                                Toast.makeText(login_activity.this,"Welcome Admin , you are Logged in Successfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent=new Intent(login_activity.this,AdminCategoryActivity.class);
                                startActivity(intent);
                            }
                            else if(parentDbName.equals("Users"))
                            {
                                Toast.makeText(login_activity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent=new Intent(login_activity.this,Home_Activity.class);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(login_activity.this,"Incorrect Password",Toast.LENGTH_SHORT ).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(login_activity.this,"Account with this"+email+"number do not exists",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent intent = new Intent(login_activity.this,Registration_activity.class);
                    startActivity(intent);
                }
          }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
   }
        });


        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_activity.this,Forget_password_Activity.class);
                startActivity(intent);
            }
        });
    }
}