package com.example.binding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //binding se chalega
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

//        textView=binding.tv1;

//        binding.setUsername("Hello WOrld this is different");
//        Toast.makeText(this, binding.getUsername(), Toast.LENGTH_SHORT).show();

        Model model=new Model("Jello Star");
        binding.setModel(model);
    }
}