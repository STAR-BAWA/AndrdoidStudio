package com.example.notifcations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //our image in large must be in bitmap

        //getResources is a type of a context
        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.icon,null);
        BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;

        Bitmap largeIcon=BitmapDrawable.getBitmap();
        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification=new Notification.Builder(this).setLargeIcon(largeIcon).setSmallIcon(R.drawable.icon).setContentText("Message New Message").setSubText("New Message From Raman").build();
    }
}