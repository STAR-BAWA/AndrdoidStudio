package com.example.project2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class user_history_adapter extends FirebaseRecyclerAdapter<user_orderHistory_modal, user_history_adapter.user_history_viewHolder> {

    public user_history_adapter(@NonNull FirebaseRecyclerOptions<user_orderHistory_modal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull user_history_adapter.user_history_viewHolder holder, int position, @NonNull user_orderHistory_modal model) {
        holder.book_price.setText("Rs." + model.getBook_price());
        holder.bookName.setText(model.getBookName());
        holder.contact.setText(model.getSeller_contact());
        String url = model.getImage();
        Picasso.get().load(url).into(holder.image);
    }

    @NonNull
    @Override
    public user_history_adapter.user_history_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_history, parent, false);
        return new user_history_viewHolder(view);
    }

    public static class user_history_viewHolder extends RecyclerView.ViewHolder {
        TextView bookName;
        TextView book_price;
        ImageView image;
        TextView contact;
        RelativeLayout layout;

        public user_history_viewHolder(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.user_history_name);
            book_price = itemView.findViewById(R.id.user_history_price);
            image = itemView.findViewById(R.id.user_history_image);
            contact = itemView.findViewById(R.id.seller_number_user_history);
        }
    }
}
