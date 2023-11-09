package com.example.sumof2numb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;
import android.webkit.WebView;
import android.speech.tts.TextToSpeech;


public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText n1;
    EditText n2;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Good morning ji", Toast.LENGTH_SHORT).show();
        b1 = findViewById(R.id.button2);
        n1 = findViewById(R.id.editTextNumber);
        n2 = findViewById(R.id.editTextNumber2);
        txt=findViewById(R.id.textView2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int sum= Integer.parseInt(n1.getText().toString()) + Integer.parseInt(n2.getText().toString());
                txt.setText("The sum is "+sum);

            }
        });
    }
}