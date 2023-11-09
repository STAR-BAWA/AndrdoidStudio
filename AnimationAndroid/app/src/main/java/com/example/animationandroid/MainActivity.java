package com.example.animationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3;
    TextView txtAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.alpha);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button4);
        txtAnim=findViewById(R.id.textView);
        //Animation an= AnimationUtils.loadAnimation(this,R.anim.fade_animation);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Animation an= AnimationUtils.loadAnimation(this,R.anim.fade_animation);
                Animation an= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                txtAnim.startAnimation(an);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation mov=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
                txtAnim.startAnimation(mov);
            }
        });
       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Animation rot=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
               txtAnim.startAnimation(rot);
           }
       });

    }
}