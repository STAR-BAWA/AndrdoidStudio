package com.example.firebasedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText edname;
    EditText edpass;
    EditText edemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button2);
        edname=(EditText) findViewById(R.id.editTextName);
        edpass=(EditText) findViewById(R.id.editTextpass);
        edemail=(EditText) findViewById(R.id.editTextmail);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase fd=FirebaseDatabase.getInstance();
                fd.getReference().child("Name").setValue(edname.getText().toString());
                Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}