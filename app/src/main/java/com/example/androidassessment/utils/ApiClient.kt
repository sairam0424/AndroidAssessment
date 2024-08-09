package com.example.androidassessment.utils


import com.example.androidassessment.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val api : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}


