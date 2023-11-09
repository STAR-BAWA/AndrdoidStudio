package com.example.bookworm_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rey.material.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {


    private ImageView fictional,nonfictional,crime,horror,ncert,dramas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

  fictional=(ImageView)findViewById(R.id.book_fictional);
  nonfictional=(ImageView)findViewById(R.id.book_non_fictional);
  crime=(ImageView)findViewById(R.id.book_crimes);
  horror=(ImageView)findViewById(R.id.book_horrors);
  ncert=(ImageView)findViewById(R.id.book_ncert);
  dramas=(ImageView)findViewById(R.id.book_Dramas);





        fictional.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
           intent.putExtra("category","fictional");
       }
   });

        nonfictional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","nonfictional");
            }
        });

        crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","crime");
            }
        });

        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","horror");
            }
        });
        ncert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","ncert");
            }
        });
        dramas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","dramas");
            }
        });
   }
}