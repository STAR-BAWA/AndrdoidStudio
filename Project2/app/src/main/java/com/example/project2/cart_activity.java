package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.HashMap;

public class cart_activity extends AppCompatActivity implements PaymentResultListener {

    RecyclerView rcv;
    cart_adapter adapter;
    TextView amount;
    Button checkout1;
    Checkout checkout = new Checkout();
    String num;
    String book_price;
    int totalAmount;
    String address;
    String Seller_contact;
    String email;
    String Address_user;
    @Override

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);

    rcv=findViewById(R.id.cart_recycle);

    rcv.setLayoutManager(new WrapContentLinearLayoutManager(this));

      Checkout.preload(getApplicationContext());

             checkout.setKeyID("rzp_test_vnFnqHrrMQf4ta");


    amount=findViewById(R.id.Amount_checkout);
    Intent it=getIntent();
     num=it.getStringExtra("Number");
     address=it.getStringExtra("Address_User");
     email=it.getStringExtra("email_user");

    DatabaseReference query = FirebaseDatabase.getInstance().getReference("User_Side").child(num).child("Cart_item");

    FirebaseRecyclerOptions<cart_modal> options = new FirebaseRecyclerOptions.Builder<cart_modal>()
            .setQuery(query, cart_modal.class)
            .build();

    adapter = new cart_adapter(options);

    rcv.setAdapter(adapter);

    checkout1=findViewById(R.id.button_checkout);
    checkout1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(cart_activity.this, "Success", Toast.LENGTH_SHORT).show();


             startPayment();

        }
    });

    // Calculate total amount
    DatabaseReference reference=FirebaseDatabase.getInstance().getReference("User_Side").child(num).child("Cart_item");
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                cart_modal model=snapshot1.getValue(cart_modal.class);
                totalAmount += Integer.parseInt(model.getBook_price());
            }
            amount.setText("Rs. "+String.valueOf(totalAmount));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Handle error
            Toast.makeText(cart_activity.this, "No user Found", Toast.LENGTH_SHORT).show();
        }
    });

    adapter.startListening();
}

  public void startPayment() {
      /**
       * Set your logo here
       */
      checkout.setImage(R.drawable.ic_baseline_menu_book_24);

      /**
       * Reference to current activity
       */
      final Activity activity = this;

      /**
       * Pass your payment options to the Razorpay Checkout as a JSONObject
       */
      try {
          JSONObject options = new JSONObject();

          options.put("name", "BookWorm");
          options.put("description", "Reference No. #123456");
          options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//          options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
          options.put("theme.color", "#3399cc");
          options.put("currency", "INR");
          options.put("amount", totalAmount+"00");//pass amount in currency subunits
          options.put("prefill.email", "gaurav.kumar@example.com");
          options.put("prefill.contact","9988776655");
          JSONObject retryObj = new JSONObject();
          retryObj.put("enabled", true);
          retryObj.put("max_count", 4);
          options.put("retry", retryObj);

          checkout.open(activity, options);

      } catch(Exception e) {
        Log.e("TAG", "Error in starting Razorpay Checkout", e);
      }
    }

   @Override
public void onPaymentSuccess(String s) {
    // Add your implementation to handle payment success scenario

    DatabaseReference reference=FirebaseDatabase.getInstance().getReference("User_Side");
    reference.child(num).child("Cart_item").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String name_cart=childSnapshot.child("BookName").getValue(String.class);
                    String price_cart=childSnapshot.child("book_price").getValue(String.class);
                    String img_address=childSnapshot.child("Image").getValue(String.class);
                    Seller_contact=childSnapshot.child("Seller_contact").getValue(String.class);

                    HashMap<String,Object> map=new HashMap<>();
                    map.put("BookName",name_cart);
                    map.put("book_price",price_cart);
                    map.put("Image",img_address);
                    map.put("Seller_contact",Seller_contact);

                    DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("User_Side").child(num).child("Order_history").push();
                    reference1.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(cart_activity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(cart_activity.this, "Failed to place order", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    DatabaseReference reference2=FirebaseDatabase.getInstance().getReference("User_Side");
                    reference2.child(Seller_contact).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Address_user=snapshot.child("Address").getValue(String.class);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    DatabaseReference reference3=FirebaseDatabase.getInstance().getReference("Vendor1");
reference3.child(Seller_contact).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("User_contact", num);
            map1.put("User_address", Address_user);
            map1.put("BookName",name_cart);
            map1.put("book_price",price_cart);
            map1.put("Image",img_address);
            map1.put("Seller_contact",Seller_contact);

            map1.put("email", email);
            DatabaseReference reference4=FirebaseDatabase.getInstance().getReference("Vendor1");

            reference4.child(Seller_contact).child("new_request").push().updateChildren(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(cart_activity.this, "Succesfully updated on Vendor side ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            finish();

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});


                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Handle error
            Toast.makeText(cart_activity.this, "No user Found", Toast.LENGTH_SHORT).show();
        }
    });
}


    @Override
    public void onPaymentError(int i, String s) {
        // Add your implementation to handle payment error scenario
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
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