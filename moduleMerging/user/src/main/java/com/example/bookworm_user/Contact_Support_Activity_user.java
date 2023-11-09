package com.example.bookworm_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.rey.material.widget.ImageView;

public class Contact_Support_Activity_user extends AppCompatActivity {

    ImageView callicon,msgicon,mailicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_support_user);

        callicon=findViewById(R.id.Callsupport);
        msgicon=findViewById(R.id.message_support);
        mailicon=findViewById(R.id.MailSupport);

        callicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dial=new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel: +916387499381"));
                startActivity(dial);
            }
        });

        msgicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msg=new Intent(Intent.ACTION_SENDTO);
                msg.setData(Uri.parse("smsto:"+ Uri.encode("+916387499381")));
                msg.putExtra("sms_body","Please Add your complaint here ");
                startActivity(msg);
            }
        });

        mailicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email=new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"kirtirajani809@gmail.com"});
                email.putExtra(Intent.EXTRA_TEXT,"Please add your complaint");
                startActivity(Intent.createChooser(email,"Complaint of the customer"));
            }
        });
    }
}