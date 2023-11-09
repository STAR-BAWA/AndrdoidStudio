package com.example.kotlin_commands_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import com.example.kotlin_commands_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
private lateinit var binding: ActivityMainBinding;
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         // Inflate the layout file and create a binding object.
     // Inflate the layout file and create a binding object.
        // Bind the binding object to the activity.
            binding= ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root);

    binding.Mytext.setText("Hello Robin");

        // Use the binding object to access the views in the layout file.

    }
}