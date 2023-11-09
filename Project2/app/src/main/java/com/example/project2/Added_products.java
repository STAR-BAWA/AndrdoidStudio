package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Added_products extends AppCompatActivity {

    RecyclerView rcv;
    VendorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added_products);
        Intent it=getIntent();
        String number=it.getStringExtra("Number");
        rcv=findViewById(R.id.Inventory_cycle);

        Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
        rcv.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference("Vendor1").child(number).child("VendorProducts");
        FirebaseRecyclerOptions<VendorModal> options = new FirebaseRecyclerOptions.Builder<VendorModal>()
                .setQuery(vendorRef, VendorModal.class)
                .build();

        adapter = new VendorAdapter(options,this);


        rcv.setAdapter(adapter);
//        Toast.makeText(this, "Hello Recycler Toast", Toast.LENGTH_SHORT).show();

        adapter.startListening();


    }
}