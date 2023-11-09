package com.example.sensor_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.metrics.Event;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager smg;
        smg=(SensorManager)this.getSystemService(SENSOR_SERVICE);
        Sensor light;
        light=smg.getDefaultSensor(Sensor.TYPE_LIGHT);
        smg.registerListener((SensorEventListener) this,light,SensorManager.SENSOR_DELAY_NORMAL);


    }
}