package com.example.readdatabasefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.SnapshotHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView4);
        t5=findViewById(R.id.textView5);
        FirebaseDatabase df=FirebaseDatabase.getInstance();
        DatabaseReference ref=df.getReference().child("Vendor1").getRef();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //take snapshot as a node with children
                for(DataSnapshot node : snapshot.getChildren())
                {
                    String name=node.child("email").getValue().toString();
//                    String password=node.getValue().toString();
//                    String phno=node.getValue().toString();
//                    String address=node.getValue().toString();
//                    String email=node.getValue().toString();

                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");


                    t1.setText(name);
//                    t2.setText(password);
//                    t3.setText(phno);
//                    t4.setText(address);
//                    t5.setText(email);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}