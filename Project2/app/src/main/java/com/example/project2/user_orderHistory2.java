package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_orderHistory2 extends AppCompatActivity {

    user_history_adapter adapter;
    RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_history2);

        rcv=findViewById(R.id.order_hhistory);

        Intent it=getIntent();
        String phoneNumber=it.getStringExtra("Number");

                rcv.setLayoutManager(new WrapContentLinearLayoutManager(getApplicationContext()));

        DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference("User_Side").child(phoneNumber).child("Order_history");
        FirebaseRecyclerOptions<user_orderHistory_modal> options = new FirebaseRecyclerOptions.Builder<user_orderHistory_modal>()
                .setQuery(vendorRef, user_orderHistory_modal.class)
                .build();

        adapter = new user_history_adapter(options);


        rcv.setAdapter(adapter);

        adapter.startListening();



    }
}