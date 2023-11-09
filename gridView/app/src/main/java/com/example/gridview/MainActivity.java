package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv=findViewById(R.id.grid_view);

        ArrayList<courseModal> courseModalArrayList =new ArrayList<>();
        courseModalArrayList.add(new courseModal("DSA", R.drawable.ic_android_black_24dp));
        courseModalArrayList.add(new courseModal("C++", R.drawable.ic_android_black_24dp));
        courseModalArrayList.add(new courseModal("JAVA", R.drawable.ic_android_black_24dp));
        courseModalArrayList.add(new courseModal("PYTHON", R.drawable.ic_android_black_24dp));

        courseGVAdapter adapter=new courseGVAdapter(this,courseModalArrayList);

        gv.setAdapter(adapter);

    }
}