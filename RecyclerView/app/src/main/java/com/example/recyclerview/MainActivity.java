package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView rcv;
ArrayList<ContactModal> arrContacts=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv=findViewById(R.id.recycleview);

        rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //setting the layout for the RecylerView
        ContactModal model=new ContactModal(com.google.android.material.R.drawable.ic_clock_black_24dp,"Name1","0540034503");
        ContactModal model2=new ContactModal(com.google.android.material.R.drawable.ic_clock_black_24dp,"Name2","0540034503");
        ContactModal model3=new ContactModal(com.google.android.material.R.drawable.ic_clock_black_24dp,"Name3","0540034503");



        arrContacts.add(new ContactModal(model.img,model.name,model.number));
         arrContacts.add(new ContactModal(model2.img,model2.name,model2.number));

         RecyclerContactAdapter rc=new RecyclerContactAdapter(this,arrContacts);
        rcv.setAdapter(rc);
    }
}