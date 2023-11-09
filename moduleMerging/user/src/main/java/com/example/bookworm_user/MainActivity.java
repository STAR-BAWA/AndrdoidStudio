package com.example.bookworm_user;

//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookworm_user.Model.Users;
import com.example.bookworm_user.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private Button join_now_btn, login_now_btn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        join_now_btn = (Button) findViewById(R.id.join_btn);
        Paper.init(this);
        loadingBar = new ProgressDialog(this);
        login_now_btn = (Button) findViewById(R.id.login_btn);
        login_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login_activity.class);
                Toast.makeText(MainActivity.this, "login ", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
            }
        });

        join_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.bookworm_user.Registration_activity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "login  toast active", Toast.LENGTH_SHORT).show();
            }
        });
        String UserEmailKey = Paper.book().read(Prevalent.UserEmailKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);
        if (UserEmailKey != "" && UserPasswordKey != "") {
            {

//
                //              AllowAccess(UserEmailKey, UserPasswordKey);

                loadingBar.setTitle("Already Logged In");
                loadingBar.setMessage("Please wait.");
                loadingBar.setCanceledOnTouchOutside(true);
                //      loadingBar.show();
            }
        }
    }
}
  /* private void AllowAccess(final String email, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.child("Users").child(email).exists())
                {
                    Users usersData = snapshot.child("Users").child(email).getValue(Users.class);
                    if (usersData.getEmail().equals(email)) {
                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(MainActivity.this, Home_Activity.class);
                            startActivity(intent);
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Account with this" + email + "number do not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent intent = new Intent(MainActivity.this, Registration_activity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

        }



*/