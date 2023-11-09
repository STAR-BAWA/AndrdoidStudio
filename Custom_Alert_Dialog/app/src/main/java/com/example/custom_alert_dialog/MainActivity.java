package com.example.custom_alert_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.custome_alert_dialog);

        dialog.show();
    }
}