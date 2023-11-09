package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
//        //creates a single button dialog box
//
//        alertDialog.setTitle("Simple one button dialog");
//        alertDialog.setMessage("have you read the T & C");
//        alertDialog.setIcon(R.drawable.ic_baseline_priority_high_24);
//
//        alertDialog.setButton("yes ", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Yes is Created", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        alertDialog.show();
//        //dialog block will be showed with this method


        AlertDialog.Builder alertdialog=new AlertDialog.Builder(MainActivity.this);
        //This class orientation is for mulitbutton dialog box
        alertdialog.setCancelable(false);
        //if we want to make the dialog box visible even after we click on some part else of
        //our screen  then we set the above command
        alertdialog.setTitle("DualButton Dialog Box");
        alertdialog.setIcon(R.drawable.ic_baseline_priority_high_24);
        alertdialog.setMessage("Dual buttons are active");
        alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"yes dialog",Toast.LENGTH_LONG);
            }
        });

        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"No selected",Toast.LENGTH_LONG);
                //simply passing this wont work since this refers to
                //the alertdialog button setnegative button method
            }
        });

        alertdialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Neutral button clicked", Toast.LENGTH_LONG).show();
            }
        });

        alertdialog.show();

    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
        alertdialog.setIcon(R.drawable.ic_baseline_priority_high_24);
        alertdialog.setPositiveButton("exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        alertdialog.show();
    }

}