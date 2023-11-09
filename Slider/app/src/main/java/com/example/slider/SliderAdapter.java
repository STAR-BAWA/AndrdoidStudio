package com.example.slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {

    List<SliderData> mSliderItems;
    SliderAdapter(Context context, ArrayList<SliderData> mSliderItems){
        this.mSliderItems=mSliderItems;
    }
    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
         View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        final SliderData data= mSliderItems.get(position);

        Picasso.get().load(data.getImageData()).into(viewHolder.img);

    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView img;
        View itemView;
        public SliderViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.myimage);
            this.itemView=itemView;
        }
    }
}