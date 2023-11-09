package com.example.api_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

    }

    fun getData() {



RetroFitInstance.apiInterfce.getData().enqueue(object : Callback<responseDataClass> {
    override fun onResponse(call: Call<responseDataClass>, response: Response<responseDataClass>) {
        if (response.isSuccessful) {
            val data = response.body() // Handle the response data here
            // Do something with the data
        } else {
            // Handle unsuccessful response
        }
    }

    override fun onFailure(call: Call<responseDataClass>, t: Throwable) {
        // Handle failure/error here
    }
})




    }

}






