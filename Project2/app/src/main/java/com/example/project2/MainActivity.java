package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button b1,user;
TextView tv1;
TextView tvTc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTc=findViewById(R.id.textViewTandC);
        tvTc.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,t_and_c.class));
                }
        });

         user=findViewById(R.id.buttonUser);
         user.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(v.getContext(),user_login.class));
             }
         });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.adminActionBar:
                startActivity(new Intent(MainActivity.this,AdminLogin.class));
                break;

            case R.id.vendorDt:
                startActivity(new Intent(MainActivity.this,sellerLogin.class));
                break;

        }
           return super.onOptionsItemSelected(item);
    }
}