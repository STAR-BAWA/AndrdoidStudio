package com.example.kotlinapplilcation_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text:TextView =findViewById(R.id.textview);
        val button:Button=findViewById(R.id.button);
        button.setOnClickListener {
            Toast.makeText(this,"Hello Kotlin",Toast.LENGTH_SHORT).show();
            text.setText("Hello kotlin")
        };
    }
}