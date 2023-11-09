package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminOrderScreen extends AppCompatActivity {

    RecyclerView rcv;
  AdminOrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_screen);

        rcv=findViewById(R.id.adminOrderRecycler);


        rcv.setLayoutManager(new LinearLayoutManager(AdminOrderScreen.this));
        DatabaseReference query = FirebaseDatabase.getInstance().getReference("Products");
        FirebaseRecyclerOptions<AdminOrderModal> options = new FirebaseRecyclerOptions.Builder<AdminOrderModal>()
                .setQuery(query, AdminOrderModal.class)
                .build();

        adapter=new AdminOrderAdapter(options);

        rcv.setAdapter(adapter);
        adapter.startListening();
        Toast.makeText(this, "hello Recycler View", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Final Message ", Toast.LENGTH_LONG).show();



    }

    @Override
protected void onStart() {
    super.onStart();
    adapter.startListening();
}

@Override
protected void onStop() {
    super.onStop();
    if(adapter != null) {
        adapter.stopListening();
    }
}
}