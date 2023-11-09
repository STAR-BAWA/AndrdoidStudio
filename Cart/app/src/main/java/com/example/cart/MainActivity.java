package com.example.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView cart_cycle;
    cartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference();
        FirebaseRecyclerOptions<CartModal> options = new FirebaseRecyclerOptions.Builder<CartModal>()
                .setQuery(vendorRef, CartModal.class)
                .build();


        cart_cycle=findViewById(R.id.cart_recycler);

        cart_cycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter=new cartAdapter(options);

        cart_cycle.setAdapter(adapter);

//        adapter.startListening();


    }

     // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}