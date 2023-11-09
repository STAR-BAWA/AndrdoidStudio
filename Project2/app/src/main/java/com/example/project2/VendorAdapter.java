package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class VendorAdapter extends FirebaseRecyclerAdapter<VendorModal,VendorAdapter.ViewHolderVendor> {

    String SellerRating;
    Context mcontext;
    public VendorAdapter(@NonNull FirebaseRecyclerOptions<VendorModal> options, Context context) {
        super(options);
        mcontext=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull VendorAdapter.ViewHolderVendor holder, int position, @NonNull VendorModal model) {
        holder.BookName.setText(model.getBookName());
        holder.book_auth.setText(model.getBook_auth());
        holder.book_price.setText("Rs."+model.getBook_price());
//        holder.BookDescription.setText(model.getBookDescription());
        holder.Seller_phone.setText(model.getSeller_phone());
         DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Vendor1").child(model.getSeller_phone());

         reference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 SellerRating=snapshot.child("Rating").getValue().toString();
                 holder.Rating.setText(SellerRating);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

        String imageUrl=model.getProduct_Image();
        Picasso.get().load(imageUrl).into(holder.product_Image);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(v.getContext(),PageDetailsVendor.class);
                it.putExtra("Desc",model.getBookDescription());
                it.putExtra("imgPath",model.getProduct_Image());
                it.putExtra("auth",model.getBook_auth());
                it.putExtra("phone",model.getSeller_phone());
                it.putExtra("Title",model.getBookName());
                v.getContext().startActivity(it);
                Toast.makeText(mcontext, "hello", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @NonNull
    @Override
    public ViewHolderVendor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_vendor_home,parent,false);
        return new ViewHolderVendor(view);
    }

    public static class ViewHolderVendor extends RecyclerView.ViewHolder{

        TextView BookName,book_auth,book_price,BookDescription,Seller_phone,Rating;
        ImageView product_Image;
        LinearLayout layout;
        public ViewHolderVendor(@NonNull View itemView) {
            super(itemView);
            BookName=itemView.findViewById(R.id.textViewBookName);
            book_auth=itemView.findViewById(R.id.textViewBookAuthor);
            book_price=itemView.findViewById(R.id.textViewprice);
            BookDescription=itemView.findViewById(R.id.textViewDescDetail);
            Seller_phone=itemView.findViewById(R.id.textViewNumber);
            Rating=itemView.findViewById(R.id.SellerRating);
            product_Image=itemView.findViewById(R.id.imageViewBook);
            layout=itemView.findViewById(R.id.HomeLayout);
        }
    }
}
