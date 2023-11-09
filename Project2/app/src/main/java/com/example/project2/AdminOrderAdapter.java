package com.example.project2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class AdminOrderAdapter extends FirebaseRecyclerAdapter<AdminOrderModal, AdminOrderAdapter.ViewHolderAdmin> {


  //0
    public AdminOrderAdapter(@NonNull FirebaseRecyclerOptions<AdminOrderModal> options) {
        super(options);
    }

    String SellerRating;
    @Override//2
    protected void onBindViewHolder(@NonNull ViewHolderAdmin holder, int position, @NonNull AdminOrderModal model) {
        holder.book_price.setText(model.getBook_price());
        holder.BookName.setText(model.getBookName());
//        holder.BookDescription.setText(model.getBookDescription());
        holder.book_auth.setText(model.getBook_auth());
//        holder.ISBN.setText(model.getISBN());
//        holder.OIN.setText(model.getOIN());
        String imageUrl=model.getProduct_Image();
        Picasso.get().load(imageUrl).into(holder.imageView);
//        String randomkey=FirebaseDatabase.getInstance().getReference().child("Vendor1").child(model.getSeller_phone()).child("VendorProducts").child(model.getISBN()).child("-NRqZ1LAmju_krmZmuh1").getKey().toString();

          DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Vendor1").child(model.getSeller_phone());

         reference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 SellerRating=snapshot.child("Rating").getValue().toString();
//                 holder.Rating.setText(SellerRating);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
                    reference.child("Vendor1").child(model.getSeller_phone()).child("VendorProducts").child(model.getOIN()).child("Approval").setValue("approved");
                    Toast.makeText(buttonView.getContext(), "sucess", Toast.LENGTH_SHORT).show();
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("BookName",model.getBook_auth());
                    map.put("book_auth",model.getBook_auth());
                    map.put("OIN",model.getOIN());
                    map.put("ISBN",model.getISBN());
                    map.put("Desc",model.getBookDescription());
                    map.put("Image",model.getProduct_Image());
                    map.put("Seller_contact",model.getSeller_phone());
                    map.put("book_price", model.getBook_price());
                    map.put("Rating",SellerRating);
                    DatabaseReference reference1=FirebaseDatabase.getInstance().getReference();


                    reference1.child("Approved_Products").child(model.getOIN()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(buttonView.getContext(), "Product Approved", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    removeItem(holder.getLayoutPosition());
                }

            }
        });
    }

      // method to remove the RecyclerView layout
    public void removeItem(int position) {
        // get a reference to the item at the specified position
        DatabaseReference reference = getRef(position);

        // remove the item from the Firebase Realtime Database
        reference.removeValue();

        // notify the adapter of the change
        notifyItemRemoved(position);
    }


    @NonNull
    @Override//1
    public ViewHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_orderlist,parent,false);
        return new AdminOrderAdapter.ViewHolderAdmin(view);
    }

    class ViewHolderAdmin extends RecyclerView.ViewHolder{
            TextView BookDescription,
                    BookName,
                    book_auth,
                    book_price,
                    vendor_contact,
                    ISBN,
                    OIN;
                ImageView imageView;
            ConstraintLayout layout;
            Switch aSwitch;
        public ViewHolderAdmin(@NonNull View itemView) {
            super(itemView);
//           BookDescription=itemView.findViewById(R.id.BookDescOderList);
           BookName=itemView.findViewById(R.id.BookNameOrderList);
           book_auth=itemView.findViewById(R.id.bookAuthOrderList);
           book_price=itemView.findViewById(R.id.BookPriceOrderList);
//           vendor_contact=itemView.findViewById(R.id.Admin_contact_vendor);
//           ISBN=itemView.findViewById(R.id.Admin_book_ISBN);
//           OIN=itemView.findViewById(R.id.textViewOIN);
//           layout=itemView.findViewById(R.id.Orderlist);
            imageView=itemView.findViewById(R.id.imageViewAdmin);
            aSwitch=itemView.findViewById(R.id.switch1);
        }
    }
}

