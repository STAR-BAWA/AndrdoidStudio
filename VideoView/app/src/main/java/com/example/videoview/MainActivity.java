package com.example.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView v;
    //asks from the permission media  in manifests AndroidManifest.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v=findViewById(R.id.videoView);
        v.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.star);
        //always name the package raw directory
        MediaController mc=new MediaController(this);
        //provides controll to the media layout
        mc.setAnchorView(v);//center the media control
        v.setMediaController(mc);//set media control to video control

    }
}