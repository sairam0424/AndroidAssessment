package com.example.androidassessment.network

import com.example.androidassessment.models.Books
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("books")
    fun getAllBooks(): Call<List<Books>>
}
