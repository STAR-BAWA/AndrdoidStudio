package com.example.project2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class inventoryDetails extends AppCompatActivity {
    TextView name, email_f, address_f, phno_f, product_sold;
    ProgressBar bar_added;
    ProgressBar bar_sold;
    String seller_number;
    int key;
    TextView Rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        Intent it = getIntent();
        bar_added = (ProgressBar) findViewById(R.id.progressBar2);
        seller_number = it.getStringExtra("phone_number");
        product_sold = findViewById(R.id.textView_Product_add_INV_DEtails);
        name = findViewById(R.id.textViewSellerName_In_details);
        email_f = findViewById(R.id.textView_email_inventory_details);
        address_f = findViewById(R.id.textView_address_inv_detail);
        phno_f = findViewById(R.id.textView_number_in_details);
        bar_sold = findViewById(R.id.progressBar);
        Rating=findViewById(R.id.Rating_inv);


        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Vendor1");

        reference.child(seller_number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rating=snapshot.child("Rating").getValue(String.class);

                Rating.setText(rating);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Toast.makeText(this, seller_number, Toast.LENGTH_SHORT).show();

        reference.child(seller_number).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1=snapshot.child("Name").getValue(String.class);
                String email_f1=snapshot.child("email").getValue(String.class);
                String address_f1=snapshot.child("Address").getValue(String.class);

//                Toast.makeText(inventoryDetails.this, name+" "+email_f+" "+address_f+" ", Toast.LENGTH_SHORT).show();

                name.setText(name1);
                email_f.setText(email_f1);
                address_f.setText(address_f1);
                phno_f.setText(seller_number);



                getItemCount(seller_number);
                getItemCount2(seller_number);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bar_added.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(v.getContext(),Added_products.class);
                it.putExtra("Number",seller_number);
                it.putExtra("Sold_prod",key);
                startActivity(it);
            }
        });
    }

    private void getItemCount2(String seller_number) {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vendor1").child(seller_number).child("new_request");
    reference.addValueEventListener(new ValueEventListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                int count = (int) snapshot.getChildrenCount();
                int maxProgress = 100;
                bar_sold.setMax(maxProgress);
                bar_sold.setProgress(count);
                product_sold.setText(Integer.toString(count));

                if (count >= maxProgress) {
                    Toast.makeText(inventoryDetails.this, "Limit exceeded", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Handle onCancelled event
        }
    });
}


    private void getItemCount(String seller_number) {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vendor1").child(seller_number).child("VendorProducts");
    reference.addValueEventListener(new ValueEventListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()) {
                int count = (int) snapshot.getChildrenCount();
                bar_added.setMax(100);
                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(bar_added, "progress", 0, count);
                progressAnimator.setDuration(3000);
                progressAnimator.start();
                bar_added.setProgress(count);
                product_sold.setText(Integer.toString(count));

                if(count==100)
                {
                    Toast.makeText(inventoryDetails.this, "cannot add more", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


}




}
