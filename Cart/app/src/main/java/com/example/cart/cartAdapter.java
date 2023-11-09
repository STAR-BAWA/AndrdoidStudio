package com.example.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

public class cartAdapter extends FirebaseRecyclerAdapter<CartModal,cartAdapter.ViewHolderCart> {


    public cartAdapter(@NonNull FirebaseRecyclerOptions<CartModal> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull cartAdapter.ViewHolderCart holder, int position, @NonNull CartModal model) {
        holder.itemName.setText(model.getItemName());
        holder.itemQuantity.setText(model.getItemQuantity());
        holder.itemPrice.setText(model.getItemPrice());

    }


    @NonNull
    @Override
    public cartAdapter.ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout,parent,false);
        return new cartAdapter.ViewHolderCart(view);
    }


    public class ViewHolderCart extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemPrice;
        TextView itemQuantity;
        public ViewHolderCart(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.Cart_book_Name);
            itemPrice=itemView.findViewById(R.id.Cart_booK_Price);
            itemQuantity=itemView.findViewById(R.id.Cart_book_qty);
        }
    }
}
