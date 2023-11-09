package com.example.api_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.stream.DoubleStream.builder
import java.util.stream.IntStream.builder
import java.util.stream.Stream.builder

object RetroFitInstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("Enter your url here").addConverterFactory(GsonConverterFactory.create()).build();

    }
    val apiInterfce by lazy {
        retrofit.create(api_interface::class.java)
    }
}