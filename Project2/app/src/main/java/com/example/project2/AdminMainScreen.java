package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.project2.databinding.ActivityAdminMainScreenBinding;

import es.dmoral.toasty.Toasty;

public class AdminMainScreen extends AppCompatActivity {
CardView order;
CardView rating;

TextView dashboard;
//ActivityAdminMainScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_screen);
        order=findViewById(R.id.adminOrder);
//        payment_option=findViewById(R.id.adminPayment);
        dashboard=findViewById(R.id.Dashboard_link);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Approving the order

                startActivity(new Intent(AdminMainScreen.this,AdminOrderScreen.class));
            }
        });

//        rating=binding.SellerRatingLayout;
            rating=findViewById(R.id.Seller_rating_layout);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),Seller_rating.class));
                Toasty.success(v.getContext(),"success",Toasty.LENGTH_LONG,true);
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(),Dashboard.class));
            }
        });

    }
}