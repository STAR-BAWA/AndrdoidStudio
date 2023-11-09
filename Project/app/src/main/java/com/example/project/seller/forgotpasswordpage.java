package com.example.project.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class forgotpasswordpage extends AppCompatActivity {

    TextInputEditText ed1;
    Button b1;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpasswordpage);

//        edp=findViewById(R.id.editTextpasswordUC);
//        edpc=findViewById(R.id.editTextpassC);

        ed1=findViewById(R.id.firstpass);
        b1=findViewById(R.id.buttonUpdate);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(forgotpasswordpage.this, ed1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}