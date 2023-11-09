package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.base.Verify;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class cart_activity_login extends AppCompatActivity {

    EditText number, password;
    Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_login);

        number=findViewById(R.id.Cart_Number);
        password=findViewById(R.id.editTextCart_verification);


        verify=findViewById(R.id.button_cart_verify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number.getText().toString().isEmpty() || number.getText().toString().length() <10 ||password.getText().toString().length()!=6 || password.getText().toString().isEmpty()){
                    Toasty.error(cart_activity_login.this, "Error Wrong Values Entered", Toast.LENGTH_SHORT,true).show();
                }
                else {
                    VerifyCart(number.getText().toString(),password.getText().toString());
                }
            }
        });


    }

    private void VerifyCart(String number_o,String password_o) {

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("User_Side");

        reference.child(number.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String get_phone_number = snapshot.child("Phone_Number").getValue(String.class);
                    String get_password = snapshot.child("password").getValue(String.class);
                    String address_user=snapshot.child("Address").getValue(String.class);
                    String email_user=snapshot.child("email").getValue(String.class);
                    if (get_phone_number.equals(number_o) && get_password.equals(password_o)) {
                        Toasty.success(cart_activity_login.this, "Sucess", Toast.LENGTH_SHORT, true).show();
                        Intent it=new Intent(cart_activity_login.this,cart_activity.class);
                        it.putExtra("Number",get_phone_number);
                        it.putExtra("Address_User",address_user);
                        it.putExtra("email_user",email_user);
                        startActivity(it);
                    }
                }
                else{
                    Toasty.error(cart_activity_login.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}