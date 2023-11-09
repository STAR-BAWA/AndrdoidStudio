package com.example.lottianimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView  laview;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.button);
        laview=findViewById(R.id.animation);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laview.setAnimation(R.raw.lottie);
                laview.playAnimation();
            }
        });

    }
}
