package com.example.splashscreendatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText edname;
    private EditText edage;
    private EditText disease;
    private Button btn;
    private  DBhandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname=(EditText) findViewById(R.id.editTextName);
        edage=(EditText) findViewById(R.id.editTextAge);
        disease=(EditText) findViewById(R.id.editTextDisease);
        btn=(Button) findViewById(R.id.button);


        db=new DBhandler(MainActivity.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientName=edname.getText().toString();
                String patient_age=edage.getText().toString();
                String patient_disease=disease.getText().toString();

                if(patientName.isEmpty()&& patient_age.isEmpty() && patient_disease.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter all details", Toast.LENGTH_LONG).show();
                    return;
                }

                db.addNewCourse(patientName,patient_age,patient_disease);
                Toast.makeText(MainActivity.this, "Database Entry Success", Toast.LENGTH_LONG).show();
                edname.setText("");
                edage.setText("");
                disease.setText("");

            }
        });

    }
}