package com.example.alert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("!Alert  ");

 // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        builder.setPositiveButton("yes", (DialogInterface.OnClickListener)(dialog,which)->{
            finish();
        });

//        builder.setPositiveButton("yes",DialogInterface.OnClickListener(dialog,which)->{
//            finish();
//        });

        builder.setNegativeButton("No",(DialogInterface.OnClickListener)(dialog,which)->{
            dialog.cancel();
        } );
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}