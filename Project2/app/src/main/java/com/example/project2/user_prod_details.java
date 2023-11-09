package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class user_prod_details extends AppCompatActivity {

    ImageView img;
    TextView bookName,book_auth,desc,amount,seller_number;
    String bookName_s,book_aut_s,desc_s,amount_s,seller_number_s;
    Button b1;
    EditText user_num;
    String OIN_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_prod_details);

        img=findViewById(R.id.imageViewuser_details);
        bookName=findViewById(R.id.bookName_user);
        book_auth=findViewById(R.id.Auth_user);
        desc=findViewById(R.id.bookDescUser);
        amount=findViewById(R.id.Amount);
        seller_number=findViewById(R.id.SellerNumber);

        Intent it=getIntent();

        user_num=findViewById(R.id.editTextPhone_user_num);
        String img_s=it.getStringExtra("BookImage");


        bookName_s=it.getStringExtra("BookName");
        book_aut_s=it.getStringExtra("bookAuth");
        desc_s=it.getStringExtra("BookDesc");
        amount_s=it.getStringExtra("BookPrice");
        seller_number_s=it.getStringExtra("Seller_num");
        OIN_s=it.getStringExtra("OIN");
        Picasso.get().load(img_s).into(img);

        book_auth.setText(book_aut_s);
        bookName.setText(bookName_s);
        desc.setText(desc_s);
        amount.setText(amount_s);
        seller_number.setText(seller_number_s);

        b1=findViewById(R.id.button_cart);


       b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("BookName", bookName_s);
        map.put("book_auth", book_aut_s);
        map.put("OIN", OIN_s);
        map.put("Desc", desc_s);
        map.put("Image", img_s);
        map.put("Seller_contact", seller_number_s);
        map.put("book_price", amount_s);

        if (user_num.getText().toString().length() == 10 && !user_num.getText().toString().isEmpty()) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User_Side");
            reference.child(user_num.getText().toString()).child("Cart_item").child(it.getStringExtra("OIN")).updateChildren(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toasty.success(user_prod_details.this, "Added to cart", Toast.LENGTH_LONG, true).show();
                                Intent intent = new Intent(user_prod_details.this, cart_activity.class);
                                intent.putExtra("map_user_prod", map);
                                startActivity(intent);
                            } else {
                                Toast.makeText(user_prod_details.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toasty.error(user_prod_details.this, "Failed", Toast.LENGTH_LONG, true).show();
                }
            });
        } else {
            Toasty.error(user_prod_details.this, "Invalid Phone number", Toast.LENGTH_SHORT).show();
        }
    }
});


        
        
    }
}