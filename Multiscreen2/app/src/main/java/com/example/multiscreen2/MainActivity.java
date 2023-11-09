package com.example.multiscreen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public  static final String msg="com.example.multiscreen2";
    EditText ed1;
    EditText ed2;
    EditText ed3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //placeOrder();
    }

    public void placeOrder(View view){
        Intent intent=new Intent(this,secondscreen.class);

        ed1=findViewById(R.id.editText1);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);

        String orderItems=ed1.getText().toString()+ed2.getText().toString()+ed3.getText().toString();
        intent.putExtra(msg,orderItems);//This is used to potray the msg as a key and the orderitems
        // as a value  this can be used in some further class to fetch the value of this class in rough terms
        startActivity(intent);

    }


}