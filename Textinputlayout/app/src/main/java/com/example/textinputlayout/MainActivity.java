package com.example.textinputlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout txl;
    TextInputEditText txe;
    Button b1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txl=findViewById(R.id.textviewId);
        b1=findViewById(R.id.button);
        txe=findViewById(R.id.editTextInput);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, txe.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,secondscree.class));
            }
        });

    }
}