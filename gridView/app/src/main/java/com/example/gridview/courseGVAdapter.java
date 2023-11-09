package com.example.gridview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class courseGVAdapter extends ArrayAdapter<courseModal> {


//    public courseGVAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<courseModal> objects) {
//        super(context, resource, textViewResourceId, objects);
//    }
     public courseGVAdapter(@NonNull Context context, ArrayList<courseModal> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView=convertView;
        if(listitemView==null)
        {
         listitemView= LayoutInflater.from(getContext()).inflate(R.layout.cart_item,parent,false);
        }
        courseModal courseModal=getItem(position);
        TextView courseTv=listitemView.findViewById(R.id.idTVCourse);
        ImageView courseIV=listitemView.findViewById(R.id.idIVcourse);

        courseIV.setImageResource(courseModal.getImage_id());
        courseTv.setText(courseModal.course_name);
        return listitemView;
    }
}
