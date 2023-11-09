package com.example.swipe_recycler;

import android.bluetooth.le.AdvertisingSetParameters;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
Context context;
private List<ViewModel> datalist;
    public Adapter (Context context, List<ViewModel> datalist){
        this.context=context;
        this.datalist=datalist;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layour,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
       holder.contact.setText(datalist.get(position).contact);
       holder.age.setText(datalist.get(position).Age);
       holder.name.setText(datalist.get(position).name);
       holder.img.setImageResource(R.drawable.ic_launcher_foreground);


    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,age,contact;
        ImageView img;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.textView_Name);
            age=itemView.findViewById(R.id.textView_age);
            contact=itemView.findViewById(R.id.textView_contact);
            img=itemView.findViewById(R.id.imageView);

            layout=itemView.findViewById(R.id.layout);

        }
    }
}
