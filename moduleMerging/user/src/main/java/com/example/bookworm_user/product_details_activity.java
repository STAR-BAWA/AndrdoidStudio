package com.example.bookworm_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class product_details_activity extends AppCompatActivity {
    android.widget.TextView BookDescription;
    android.widget.TextView BookName;
    android.widget.TextView book_price,seller_phno;
    Button add_to_cart_btn;
    private String bookID="";
    String imgUrl;
   String qty="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

//        bookID=getIntent().getStringExtra("ISBN");

        add_to_cart_btn=findViewById(R.id.ButtonAddToCart);
        Intent intent = getIntent();
        BookName=findViewById(R.id.TextViewProductTitle);
        BookDescription=findViewById(R.id.TextViewProductDetails);
        book_price=findViewById(R.id.TextViewProductPrice);
        String name=intent.getStringExtra("BookName");
        String description=intent.getStringExtra("BookDescription");
        String price=intent.getStringExtra("book_price");
        String Seller_phno=intent.getStringExtra("Seller_phone");
        BookName.setText(name);
        BookDescription.setText(description);
        book_price.setText(price);

        Toast.makeText(this, "hi toast", Toast.LENGTH_SHORT).show();
        add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // HashMap<String,Object>map=new HashMap<>();
                //map.put("bookName",name);
                //map.put("book_price",price);

                addToCart(name,price);

            }
        });
    }
    private void addToCart(String bname, String bprice){
        CartItem cartItem=new CartItem();
//        Intent intent = new Intent(product_details_activity.this, ShoppingCart.class);
       /// intent.putExtra("cart_item", cartItem);

      //  startActivity(intent);
        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("Users").child("+1916387499381").child("UserCart").push();
        cartRef.setValue(cartItem);
        String cartItemId = cartRef.getKey();
        Intent intent = new Intent(product_details_activity.this, ShoppingCart.class);

//        Toast.makeText(this, qty, Toast.LENGTH_SHORT).show();
        intent.putExtra("itemName",bname);
        intent.putExtra("itemPrice",bprice);
        intent.putExtra("itemQuantity",qty);

        startActivity(intent);
        Toast.makeText(this,"Added to cart ",Toast.LENGTH_SHORT).show();


    }


}
