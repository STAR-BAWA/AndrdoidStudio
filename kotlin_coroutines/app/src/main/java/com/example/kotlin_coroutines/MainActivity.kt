package com.example.kotlin_coroutines

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.kotlin_coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding;
    private var count=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=ActivityMainBinding.inflate(layoutInflater);

        setContentView(binding.root);

        binding.button.setOnClickListener {
            updateCounter();

        }

        binding.button2.setOnClickListener{
            ExecuteLongTask();
        }

    }
    fun updateCounter(){
        binding.MyTextView.text= "${count++}"
        Log.d(TAG,"${Thread.currentThread().name}")

    }
    fun ExecuteLongTask(){
        //new thread for threading
//        thread(start=true){
//        for(i in 1..1000000000L){
//
//        }

       
        GlobalScope.launch(Dispatchers.IO) {
            for (i in 1..1000000000L) {
                // Perform long-running task
            }
            Log.d(TAG,"${Thread.currentThread().name}")

        }
        CoroutineScope(Dispatchers.Main).launch{
            Log.d(TAG,"${Thread.currentThread().name}")
        }

        MainScope().launch(Dispatchers.Default) {
             Log.d(TAG,"${Thread.currentThread().name}")
        }
    }
}




