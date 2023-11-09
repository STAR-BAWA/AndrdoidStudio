package com.example.project.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.seller.forgotpasswordpage;

public class sellerLogin extends AppCompatActivity {

    EditText edmail,edpass;
    Button login  ;
    TextView forgotpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        edmail=findViewById(R.id.editTextmail);
        edpass=findViewById(R.id.editTextpass);
        forgotpass=findViewById(R.id.forgotpassword);
        login=findViewById(R.id.buttonlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it=new Intent(sellerLogin.this,.class);
//                startActivity(it);
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sellerLogin.this, forgotpasswordpage.class));

            }
        });

    }
}