package com.example.divisionexception;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1;
    TextView user_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.editTextNum1);
        ed2=(EditText) findViewById(R.id.editTextNum2);
        b1=(Button)findViewById(R.id.button) ;
       user_result=(TextView) findViewById(R.id.textView4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double num1,num2,result;
                num1 = Double.parseDouble(ed1.getText().toString());
                num2 = Double.parseDouble(ed2.getText().toString());
                try{
                result=num1/num2;
                user_result.setText(Double.toString(result));
            }
                catch (Exception ex)
                {   ed2.setText("");
                    user_result.setText("We cant divide a number by zero");

                }
                finally {
                    ed1.setText("");
                    ed2.setText("");
                }
            }
        });


    }
}