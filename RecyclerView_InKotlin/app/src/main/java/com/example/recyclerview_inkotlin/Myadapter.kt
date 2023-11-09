package com.example.recyclerview_inkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Myadapter(private val itemList:List<Model>): RecyclerView.Adapter<Myadapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.findViewById(R.id.textView1);
        val age :TextView = itemView.findViewById(R.id.textView2);
        val profession :TextView =itemView.findViewById(R.id.textView3);
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_file,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.age.text= currentItem.age.toString();
        holder.name.text=currentItem.Name.toString()
        holder.profession.text=currentItem.profession.toString();


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}

