
package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.example.project2.databinding.ActivityUserHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_home extends AppCompatActivity {

//    ActivityUserHomeBinding binding;
    user_home_adapter adapter;
    RecyclerView rcv;
    FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

            rcv=findViewById(R.id.Recycler_user);
//
//
        rcv.setLayoutManager(new WrapContentLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//
        DatabaseReference vendorRef = FirebaseDatabase.getInstance().getReference().child("Approved_Products");
        FirebaseRecyclerOptions<user_home_modal> options = new FirebaseRecyclerOptions.Builder<user_home_modal>()
                .setQuery(vendorRef, user_home_modal.class)
                .build();
//
        button=findViewById(R.id.floatingActionButton2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),cart_activity_login.class));
            }
        });

//
        adapter = new user_home_adapter(options);

        rcv.setAdapter(adapter);
        Toast.makeText(this, "Hello Recycler Toast", Toast.LENGTH_SHORT).show();
//
        Log.d("MyActivity", "onCreate called");

adapter.startListening();
//        adapter.startListening();
//
    }


//@Override
//public void onStart() {
//    super.onStart();
//
//    // start listening for changes
//    adapter.startListening();
//}
//
//@Override
//public void onStop() {
//    super.onStop();
//
//    // stop listening for changes
//    adapter.stopListening();
//}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId())
        {
            case R.id.user_profile:
                startActivity(new Intent(user_home.this,user_profile.class));
                break;

            case R.id.order_hhistory:
                startActivity(new Intent(user_home.this,user_orderHistory.class));
                break;
            case R.id.customer_support:
                startActivity(new Intent(user_home.this,contact_support.class));

        }

        return super.onOptionsItemSelected(item);
    }
}