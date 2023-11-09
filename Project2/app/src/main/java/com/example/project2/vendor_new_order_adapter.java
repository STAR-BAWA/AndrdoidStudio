package com.example.project2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class vendor_new_order_adapter extends FirebaseRecyclerAdapter<vendor_new_order_modal,vendor_new_order_adapter.vendor_new_order_viewHolder> {

    public vendor_new_order_adapter(@NonNull FirebaseRecyclerOptions<vendor_new_order_modal> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull vendor_new_order_adapter.vendor_new_order_viewHolder holder, int position, @NonNull vendor_new_order_modal model) {

        holder.bookname.setText(model.getBookName());
        holder.price.setText(model.getBook_price());
        holder.user_contact.setText(model.getUser_contact());

        String url = model.getImage();
        Picasso.get().load(url).into(holder.img);

      holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Do you want to dispatch this item to "+model.getUser_contact()+"Whose Address is "+model.getUser_address());

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Remove the item from the RecyclerView and notify the adapter
                    removeItem(position);
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();

        return true;
    }
    private void removeItem(int position) {
    // Remove the item from the dataset
        DatabaseReference ref=getRef(position);
        ref.removeValue();
//    getSnapshots().getSnapshot(position).getRef().removeValue();
    // Notify the adapter about the item removal
    notifyItemRemoved(position);
}
});



    }

    @NonNull
    @Override
    public vendor_new_order_adapter.vendor_new_order_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_new_order_layout,parent,false);
        return new vendor_new_order_adapter.vendor_new_order_viewHolder(view);
    }

    public  class vendor_new_order_viewHolder extends RecyclerView.ViewHolder {

      ImageView img;

      TextView price,bookname,user_contact;

        RelativeLayout layout;
        public vendor_new_order_viewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.new_oder_layout);
            img=itemView.findViewById(R.id.new_product_image);
            price=itemView.findViewById(R.id.new_product_price);
            bookname=itemView.findViewById(R.id.new_order_name);
            user_contact=itemView.findViewById(R.id.new_order_contact);

        }
    }
}

