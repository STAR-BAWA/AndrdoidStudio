package com.example.bookworm_user;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class User_Main_Product_Adapter extends FirebaseRecyclerAdapter<User_Main_Product_Modal,User_Main_Product_Adapter.ViewHolderUser> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public User_Main_Product_Adapter(@NonNull FirebaseRecyclerOptions<User_Main_Product_Modal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull User_Main_Product_Adapter.ViewHolderUser holder, int position, @NonNull User_Main_Product_Modal model) {
        holder.book_price.setText(model.getBook_price());
        holder.BookName.setText(model.getBookName());
        holder.BookDescription.setText(model.getBookDescription());
        holder.book_auth.setText(model.getBook_auth());
 //       holder.ISBN.setText(model.getISBN());

    holder.layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Hello Details", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), product_details_activity.class);
            intent.putExtra("BookName", model.getBookName());
            intent.putExtra("BookDescription", model.getBookDescription());
            intent.putExtra("bookAuth", model.getBook_auth());
            intent.putExtra("book_price", model.getBook_price());
            intent.putExtra("ISBN",model.getISBN());
            v.getContext().startActivity(intent);


        //    Toast.makeText(v.getContext(), model.getBookName(), Toast.LENGTH_SHORT).show();
         //   it.putExtra("name",model.getBookName());
//            it.putExtra("b_desc",model.getBookDescription());


        }
    });


//        String imageuri=null;
//        imageuri=model.getImg();

    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_user_main_view,parent,false);
        return new ViewHolderUser(view);
    }
    class ViewHolderUser extends RecyclerView.ViewHolder
    {
       TextView BookDescription,BookName,ISBN,Seller_phone,book_auth,book_price;
        ImageView img;
        RelativeLayout layout;
        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);

            BookName=itemView.findViewById(R.id.b_name);
            BookDescription=itemView.findViewById(R.id.b_description);
            book_auth=itemView.findViewById(R.id.b_Author);
            book_price=itemView.findViewById(R.id.b_Price);
            img=itemView.findViewById(R.id.img1);
            layout=itemView.findViewById(R.id.BookDetails);
        }
    }
}
