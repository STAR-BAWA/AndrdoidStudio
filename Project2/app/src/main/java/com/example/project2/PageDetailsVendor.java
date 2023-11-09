package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

public class PageDetailsVendor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_details_vendor);
        
        Intent FetchIntent=getIntent();
        String desc=FetchIntent.getStringExtra("Desc").toString();
        String imgpath=FetchIntent.getStringExtra("imgPath");
        String auth=FetchIntent.getStringExtra("auth");
        String B_name=FetchIntent.getStringExtra("Title");
        String contact=FetchIntent.getStringExtra("phone");
        TextView descView=findViewById(R.id.textViewDescDetail);
        TextView bookName=findViewById(R.id.TextBookTitleDetails);
        TextView bookAuth=findViewById(R.id.BookAuthorDetails);
        TextView SellerNum=findViewById(R.id.VendorPhNumberDetails);

        ShapeableImageView img=findViewById(R.id.shapeable_image_view);
        bookName.setText(B_name);
        bookAuth.setText(auth);
        SellerNum.setText(contact);
        descView.setText(desc);

        SellerNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent dial=new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel: +91"+contact));
                startActivity(dial);
            }
        });
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        Picasso.get().load(imgpath).into(img);
    }
}