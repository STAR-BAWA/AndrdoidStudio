package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class vendor_new_order extends AppCompatActivity {

    RecyclerView rcv;
    vendor_new_order_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_new_order);

        rcv=findViewById(R.id.new_order_cycler);

        rcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference("Products");
        FirebaseRecyclerOptions<vendor_new_order_modal> options = new FirebaseRecyclerOptions.Builder<vendor_new_order_modal>()
                .setQuery(vendorRef, vendor_new_order_modal.class)
                .build();

        adapter = new vendor_new_order_adapter(options);



        rcv.setAdapter(adapter);
//        Toast.makeText(getContext(), "Hello Recycler Toast", Toast.LENGTH_SHORT).show();

        adapter.startListening();

    }
}