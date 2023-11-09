package com.example.imagepicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView Galleryimg;
Button b1;
public final int Gallery_code=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Galleryimg=findViewById(R.id.imggallery);
        b1=findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent(Intent.ACTION_PICK);//to provide the pick screen
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //for user any other image outside his app is external content URI

                startActivityForResult(gallery,Gallery_code);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK)
        {
            if(resultCode==Gallery_code)
            {
                Galleryimg.setImageURI(data.getData());
            }

        }
    }
}