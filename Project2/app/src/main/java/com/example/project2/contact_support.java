package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class contact_support extends AppCompatActivity {

    ImageView callicon,msgicon,whatsapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_support);

        callicon=findViewById(R.id.Callsupport);
        msgicon=findViewById(R.id.message_support);
        whatsapp=findViewById(R.id.MailSupport);

        callicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dial=new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel: +918318407131"));
                startActivity(dial);
            }
        });

        msgicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msg=new Intent(Intent.ACTION_SENDTO);
                msg.setData(Uri.parse("smsto:"+Uri.encode("+918318407131")));
                 msg.putExtra("sms_body","Please Add your complaint here ");
                 startActivity(msg);
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent itmsg=new Intent();
               itmsg.setPackage("com.whatsapp");
               itmsg.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
               itmsg.setData(Uri.parse("smsto:"+Uri.encode("+919721144209")));
               itmsg.putExtra("sms_body","Please Add your complaint here ");
               itmsg.setType("text/plain");
               startActivity(itmsg);
            }
        });
    }
}