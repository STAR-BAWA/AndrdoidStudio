package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.project2.databinding.ActivityMainBinding;
import com.example.project2.databinding.ActivitySellerRatingBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Seller_rating extends AppCompatActivity {

    RecyclerView rating_view;
//    ActivitySellerRatingBinding binding;
    Rating_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_rating);

//        rating_view=binding.ratingRecyler;

        rating_view=findViewById(R.id.rating_recyler);
        rating_view.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference query = FirebaseDatabase.getInstance().getReference("Vendor1");
        FirebaseRecyclerOptions<Rating_modal> options = new FirebaseRecyclerOptions.Builder<Rating_modal>()
                .setQuery(query, Rating_modal.class)
                .build();

        adapter= new Rating_adapter(options);
        rating_view.setAdapter(adapter);

        adapter.startListening();
    }
}