package com.example.bookworm_user;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingCart extends AppCompatActivity {

    RecyclerView cart_cycle;
    cartAdapter adapter;
    TextView totalTextView;
    Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("Users").child("+1916387499381").child("UserCart");
        FirebaseRecyclerOptions<CartItem> options = new FirebaseRecyclerOptions.Builder<CartItem>()
                .setQuery(cartRef, CartItem.class)
                .build();


        cart_cycle=findViewById(R.id.cart_recycler);
totalTextView=findViewById(R.id.total_text_view);
checkout=findViewById(R.id.idBtnPay);
        cart_cycle.setLayoutManager(new LinearLayoutManager(ShoppingCart.this));

        adapter=new cartAdapter(options);

        cart_cycle.setAdapter(adapter);

//        adapter.startListening();
cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        int totalAmount=0;
        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
            CartItem cartItem=snapshot.getValue(CartItem.class);
            totalAmount+=Integer.parseInt(cartItem.getItemPrice())*Integer.parseInt(cartItem.getItemQuantity());

        }
        totalTextView.setText(String.valueOf(totalAmount));
    }



    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

});
checkout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ShoppingCart.this,payment_activity.class);
       intent.putExtra("totalAmount",totalTextView.getText().toString());
        startActivity(intent);
    }
});
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