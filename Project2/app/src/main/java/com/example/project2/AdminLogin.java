package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class AdminLogin extends AppCompatActivity {
    
    EditText adminpassword;
    EditText adminID;
    Button adminlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    

        String password="9999";
        String adminUID="admin@123";

        adminlogin=findViewById(R.id.buttonAdminLogin);

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  adminpassword=findViewById(R.id.adminPasccode);
        adminID=findViewById(R.id.adminUserId);

        String enteredID=adminID.getText().toString();
        String enteredCode=adminpassword.getText().toString();

//                Toast.makeText(AdminLogin.this, "Clicked", Toast.LENGTH_SHORT).show();

            if((enteredCode.equals(password)) && (enteredID.equals(adminUID)))
            {
                Toasty.success(AdminLogin.this, "Success", Toast.LENGTH_SHORT,true).show();
                startActivity(new Intent(AdminLogin.this,AdminMainScreen.class));
            }
            else{
                Toasty.error(AdminLogin.this, "Wrong values entered", Toast.LENGTH_SHORT,true).show();
            }
            }
        });
        
    }
}