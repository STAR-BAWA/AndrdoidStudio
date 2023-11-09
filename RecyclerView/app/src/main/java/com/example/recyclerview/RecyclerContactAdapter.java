package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerContactAdapter  extends  RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>{
    //3.3
    ArrayList<ContactModal>arrayList;
    Context context;
    public RecyclerContactAdapter(Context context, ArrayList<ContactModal> contactlist)
    {
        this.context=context;//context generally helps in formation of links within the classes
        arrayList=contactlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //here parent means on who we have to apply the inflater
        View view= LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        //LayoutInflater.from() means that calling the layoutinflator in terms of the RecyclerContactApdater class
        //then we use the inflate(our intent ,parent ,attachroot:false;
        //if we set the attach root to true then the purpose of recycler view is defeated that is it will keep each view
        //in it's active memory instead of reusing views
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1;
        TextView t2;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //t1=findViewById();
            //findViewByID won't work since this is not a
            //activity class
            //it's a support class
            //to acess the contentns of the R.layout.contact view  holder here
            //we use the following
         t1=itemView.findViewById(R.id.textView_contact);
         t2=itemView.findViewById(R.id.textView_name);
         img=itemView.findViewById(R.id.imageView);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerContactAdapter.ViewHolder holder, int position) {
        //binds the new text to the textView or image etc
        //holder contains all the ids of the elements
        holder.img.setImageResource(arrayList.get(position).img);
        holder.t1.setText(arrayList.get(position).name);
        holder.t2.setText(arrayList.get(position).number);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
        //if returned 0 then it will not scroll no element will be inesrted
    }
}
