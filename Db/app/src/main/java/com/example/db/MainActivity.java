package com.example.db;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText edname;
    EditText edphno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.example.db.DatabaseHandler db = new com.example.db.DatabaseHandler(this);
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        edname = findViewById(R.id.EditTextName);
        edphno = findViewById(R.id.EditTextPhno2);
        db.addContact(new com.example.db.Contact(edname.toString(), edphno.toString()));
        //db.addContact(new com.example.databaseconnector.Contact(edname.toString(), edphno.toString()));
        //db.addContact(new com.example.databaseconnector.Contact("Tommy", "9522222222"));
        //db.addContact(new com.example.databaseconnector.Contact("Karthik", "9533333333"));
// Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<com.example.db.Contact> contacts = db.getAllContacts();
        for (com.example.db.Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber();
// Writing Contacts to log
            Log.d("Name: ", log);

            Button b1,b2;
            b1 = (Button) findViewById(R.id.button);
            b2=(Button)findViewById(R.id.button2);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                    db.addContact(new com.example.db.Contact(edname.toString(), edphno.toString()));
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it=new Intent(getApplicationContext(),show_data.class);
                    it.putExtra("name",cn.getName());
                    it.putExtra("ID",cn.getID());
                    it.putExtra("phno",cn.getPhoneNumber());
                    startActivity(it);
                }
            });
        }
    }}
