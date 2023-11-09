package com.example.project2;

import android.content.Intent;
import android.media.Image;
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
import com.squareup.picasso.Picasso;

public class user_home_adapter extends FirebaseRecyclerAdapter<user_home_modal, user_home_adapter.user_home_ViewHolder> {

    public user_home_adapter(@NonNull FirebaseRecyclerOptions<user_home_modal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull user_home_ViewHolder holder, int position, @NonNull user_home_modal model) {
        holder.Rating.setText("Seller Rating "+model.getRating()+"  *  ");
        holder.book_auth.setText(model.getBook_auth());
        holder.BookName.setText(model.getBookName());
        holder.book_price.setText("Rs. " + model.getBook_price());
        holder.Seller_contact.setText(model.getSeller_contact());

        String url = model.getImage();
        Picasso.get().load(url).into(holder.img);




               holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(v.getContext(), user_prod_details.class);
                    it.putExtra("BookName", model.getBookName());
                    it.putExtra("bookAuth", model.getBook_auth());
                    it.putExtra("BookPrice", model.getBook_price());
                    it.putExtra("BookImage", model.getImage());
                    it.putExtra("BookDesc", model.getDesc());
                    it.putExtra("Seller_num", model.getSeller_contact());
                    it.putExtra("OIN", model.getOIN());

                    v.getContext().startActivity(it);
                }
            });


    }

    @NonNull
    @Override
    public user_home_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_home_main, parent, false);
        return new user_home_adapter.user_home_ViewHolder(view);
    }


    public class user_home_ViewHolder extends RecyclerView.ViewHolder {

        TextView Rating,book_auth,BookName,book_price,Seller_contact;
        ImageView img;
        LinearLayout layout;
        public user_home_ViewHolder(@NonNull View itemView) {
            super(itemView);

            Rating=itemView.findViewById(R.id.Rating_user);
            book_auth=itemView.findViewById(R.id.b_Author);
            BookName=itemView.findViewById(R.id.b_name);
            book_price=itemView.findViewById(R.id.b_Price);
            Seller_contact=itemView.findViewById(R.id.seller_number_user);
            img=itemView.findViewById(R.id.img_user);
            layout=itemView.findViewById(R.id.HomeLayout_user);

        }
    }
}