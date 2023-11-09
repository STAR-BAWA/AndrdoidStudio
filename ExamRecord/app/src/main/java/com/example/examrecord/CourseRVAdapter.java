package com.example.examrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {
Context context;
ArrayList<CourseModal> arrcourse;
    public CourseRVAdapter(Context context,ArrayList<CourseModal>arrcourse){
        this.context=context;
        this.arrcourse=arrcourse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.course_rv_item,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1.setText(arrcourse.get(position).getCourseName());
        holder.tv2.setText(arrcourse.get(position).getCourseDuration());
        holder.tv3.setText(arrcourse.get(position).getCourseTracks());
        holder.tv3.setText(arrcourse.get(position).getCourseDescription());



    }

    @Override
    public int getItemCount() {
        return arrcourse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3,tv4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //finding the view of the linked Viewholder class
            itemView.findViewById(R.id.idTVCourseDescription);
            itemView.findViewById(R.id.idTVCourseDuration);
            itemView.findViewById(R.id.idTVCourseName);
            itemView.findViewById(R.id.idTVCourseTracks);

        }
    }
}
