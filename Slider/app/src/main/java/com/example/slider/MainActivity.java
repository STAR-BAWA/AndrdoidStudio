package com.example.slider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.slider.Slider;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     String url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png";
    String url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
    String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SliderView sliderView=findViewById(R.id.localSlider);
           ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
           sliderDataArrayList.add(new SliderData(url1));
           sliderDataArrayList.add(new SliderData(url2));
           sliderDataArrayList.add(new SliderData(url3));

           SliderAdapter adapter=new SliderAdapter(this,sliderDataArrayList);

            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
            sliderView.setSliderAdapter(adapter);

               // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();



    }
}