package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class user_orderHistory extends AppCompatActivity {


    EditText number, password;
    Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_history);


          number=findViewById(R.id.Cart_Number);
        password=findViewById(R.id.editTextCart_verification);


        verify=findViewById(R.id.button_cart_verify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number.getText().toString().isEmpty() || number.getText().toString().length() <10 ||password.getText().toString().length()!=6 || password.getText().toString().isEmpty()){
                    Toasty.error(user_orderHistory.this, "Error Wrong Values Entered", Toast.LENGTH_SHORT,true).show();
                }
                else {
                    user_orderHistory_fun(number.getText().toString(),password.getText().toString());
                }
            }

        });


    }

            private void user_orderHistory_fun(String number, String password) {

                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User_Side");

        reference.child(number).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {
                    String get_phone_number = snapshot.child("Phone_Number").getValue(String.class);
                    String get_password = snapshot.child("password").getValue(String.class);
                    if (get_phone_number.equals(number) && get_password.equals(password)) {
                        Toasty.success(user_orderHistory.this, "Sucess", Toast.LENGTH_SHORT, true).show();
                        Intent it=new Intent(user_orderHistory.this,user_orderHistory2.class);
                        it.putExtra("Number",get_phone_number);

                        startActivity(it);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}