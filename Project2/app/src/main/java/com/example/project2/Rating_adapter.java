package com.example.project2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Handler;

import es.dmoral.toasty.Toasty;

public class Rating_adapter extends FirebaseRecyclerAdapter<Rating_modal,Rating_adapter.Rating_View_holder> {


    int rating;
    DatabaseReference referenceGlobal= (DatabaseReference) FirebaseDatabase.getInstance().getReference("Vendor1");

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Rating_adapter(@NonNull FirebaseRecyclerOptions<Rating_modal> options) {
        super(options);
    }

    @NonNull
    @Override
    public Rating_View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_rating,parent,false);

        return new Rating_adapter.Rating_View_holder(view);

    }

    @Override
protected void onBindViewHolder(@NonNull Rating_View_holder holder, int position, @NonNull Rating_modal model) {
    holder.Name.setText(model.getName());
    holder.Phone_Number.setText(model.getPhone_Number());
    holder.Rating.setText(model.getRating());
    holder.star.setImageResource(R.drawable.ic_baseline_star_24);
    holder.add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Vendor1").child(model.getPhone_Number());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    rating=Integer.parseInt(snapshot.child("Rating").getValue(String.class));
                    if(rating >=0 && rating<=4) {


                        rating++;
                        DatabaseReference reference_local=FirebaseDatabase.getInstance().getReference("Vendor1").child(model.getPhone_Number());
                        reference_local.child("Rating").setValue(Integer.toString(rating));

                    }
                    else{
                        Toasty.info(v.getContext(),"Value Cannot be more than 5", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("TAG", "onCancelled", error.toException());
                }
            });
        }
    });

    holder.sub.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Vendor1").child(model.getPhone_Number());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    rating=Integer.parseInt(snapshot.child("Rating").getValue(String.class));
                    if(rating>=1 && rating<=5)
                    {
                        rating--;
                        DatabaseReference reference_local=FirebaseDatabase.getInstance().getReference("Vendor1").child(model.getPhone_Number());
                        reference_local.child("Rating").setValue(Integer.toString(rating));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("TAG", "onCancelled", error.toException());
                }
            });

        }
    });
}

    class Rating_View_holder extends RecyclerView.ViewHolder {


        TextView Name,Phone_Number,Rating;
        Button add,sub;
        ImageView star;
        public Rating_View_holder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Rating_Seller_name);
            Phone_Number=itemView.findViewById(R.id.Rating_seller_number);
            Rating=itemView.findViewById(R.id.Rating_rating);
            star=itemView.findViewById(R.id.imageView_rating);
            add=itemView.findViewById(R.id.button_upgrade);
            sub=itemView.findViewById(R.id.button_DegradeSeller);

        }


    }
}
