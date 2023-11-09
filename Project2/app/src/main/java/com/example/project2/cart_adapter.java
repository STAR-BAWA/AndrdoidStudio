package com.example.project2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class cart_adapter  extends FirebaseRecyclerAdapter<cart_modal,cart_adapter.cart_viewHolder> {
    /**

     */


    public cart_adapter(@NonNull FirebaseRecyclerOptions<cart_modal> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull cart_adapter.cart_viewHolder holder, int position, @NonNull cart_modal model) {

        holder.book_price.setText("Rs."+model.getBook_price());
        holder.BookName.setText(model.getBookName());
        String url=model.getImage();

        Picasso.get().load(url).into(holder.Image);

        holder.rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.getLayoutPosition());
            }
        });

    }
    public void remove(int itempos){

        DatabaseReference reference= getRef(itempos);

        reference.removeValue();
           notifyItemRemoved(itempos);
    }

    @NonNull
    @Override
    public cart_adapter.cart_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout,parent,false);
        return new cart_adapter.cart_viewHolder(view);
    }

    public  class cart_viewHolder extends RecyclerView.ViewHolder {

        TextView BookName,book_price;
        ImageView Image;
        Button rem;
        RelativeLayout layout;
        public cart_viewHolder(@NonNull View itemView) {
            super(itemView);

            BookName=itemView.findViewById(R.id.cart_item_name);
            book_price=itemView.findViewById(R.id.cart_item_price);
            Image=itemView.findViewById(R.id.cart_item_image);
            rem=itemView.findViewById(R.id.cart_item_remove_button);
            layout=itemView.findViewById(R.id.cart_layout);
        }
    }
}
