package com.example.project_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Vendor_activity2 extends AppCompatActivity {

  public  String arr[]={"Male","Female","other","Select your gender"};
    public Spinner spn;
    EditText edpincode,edstate;
    EditText postalAddress;
    String gen;
//    public String gen;
    TextView tv1;
    Button save;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor2);
        spn=findViewById(R.id.spinner);
//
//
        ArrayAdapter<String> adp=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arr);
        spn.setAdapter(adp);
        edpincode=findViewById(R.id.editTextPincode);
        String pincode=edpincode.getText().toString();
        edstate=findViewById(R.id.editTextState);
        String state=edstate.getText().toString();
//////
        postalAddress=findViewById(R.id.editTextTextPostalAddress);
        String address=postalAddress.getText().toString();
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             gen=arr[position];
            }
//
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            //nothing part
               String option= arr[arr.length-1];

            }
        });
////
        Intent it=getIntent();
        String name=it.getStringExtra("uname").toString();
        String phno=it.getStringExtra("uphno").toString();
        String edid=it.getStringExtra("uid").toString();
        String upwd=it.getStringExtra("upwd").toString();
        String udob=it.getStringExtra("u_dob").toString();
        String udor=it.getStringExtra("u_dor").toString();
        String uimg=it.getStringExtra("u_img").toString();
        String u_city=it.getStringExtra("u_city").toString();
        String email=it.getStringExtra("mail").toString();

        edpincode=findViewById(R.id.editTextPincode);
        String pincodeloc=edpincode.toString();

        tv1=findViewById(R.id.textView3);
        tv1.setText(gen);

//
        save=findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhandler db=new DBhandler(Vendor_activity2.this);

                ArrayList<VendorDetailsDB> arr=new ArrayList<>();
//                VendorDetailsDB vdb=new VendorDetailsDB(name,email,upwd,postalAddress.getText().toString(),udor,udob,gen,u_city,pincodeloc,phno,uimg,state);
                 VendorDetailsDB vdb=new VendorDetailsDB(name,email,upwd,address,udor,udob,gen,u_city,pincodeloc,phno,uimg,state);
                db.addDetails(vdb);

                Toast.makeText(Vendor_activity2.this, "Data Saved", Toast.LENGTH_LONG).show();

            }
        });

    }
}
