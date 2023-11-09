package com.example.calling_message_mail_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView phone,message,mail,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone=findViewById(R.id.imagePhone);
        message=findViewById(R.id.imageViewMessage);
        mail=findViewById(R.id.imageviewEmail);
        share=findViewById(R.id.imageViewShare);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dial=new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel: +919721144209"));
                startActivity(dial);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent message=new Intent(Intent.ACTION_SENDTO);
                message.setData(Uri.parse("smsto:"+Uri.encode("+919721144209")));
                message.putExtra("sms_body","please solve this issue asap ");
                startActivity(message);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email=new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");

                //particular set type to invoke the mail client
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"harshbawa@gmail.com","saistarbawa@gmail.com"});
                //to send the mail to people
                email.putExtra(Intent.EXTRA_SUBJECT,"This is a simple subject of android");
                email.putExtra(Intent.EXTRA_TEXT,"This is a simple mail send by android studio");
                startActivity(Intent.createChooser(email,"Via Email"));
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Andro_share=new Intent(Intent.ACTION_SEND);
                Andro_share.setType("text/plain");
                Andro_share.putExtra(Intent.EXTRA_TEXT,"https://www.tutorialspoint.com/android/android_alert_dialoges.htm");
                startActivity(Andro_share);
            }
        });
    }
}