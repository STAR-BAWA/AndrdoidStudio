package com.example.api_retrofit

import retrofit2.Call
import retrofit2.http.GET

interface api_interface {

    @GET("gimme/")
    fun getData():Call<responseDataClass>

}