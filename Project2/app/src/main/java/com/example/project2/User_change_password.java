package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class User_change_password extends AppCompatActivity {

    EditText ed1,ed_contact,ed2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change_password);

        ed1=findViewById(R.id.user_change_pass1);
        ed2=findViewById(R.id.user_change_pass2);
        ed_contact=findViewById(R.id.user_change_pass_contact);
        b1=findViewById(R.id.button_user_change);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().isEmpty() || ed2.getText().toString().isEmpty() || ed_contact.getText().toString().isEmpty())
                {
                Toasty.info(v.getContext(), "Info Missing Please fill all the details", Toast.LENGTH_SHORT,true).show();
                }

        if(ed_contact.getText().toString().length()>10 || ed_contact.getText().toString().length()<10)
        {
            Toasty.info(v.getContext(), "Information is invalid please recheck", Toast.LENGTH_SHORT).show();
        }

        else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User_Side");

            reference.child(ed_contact.getText().toString()).child("password").setValue(ed2.getText().toString());

        }

            }
        });



    }

    @Override
    public void onBackPressed() {
//        finishActivity();
    }
}