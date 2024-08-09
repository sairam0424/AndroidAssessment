package com.example.androidassessment
//import com.example.android assessment.models.Books
import com.example.androidassessment.models.Data
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("books")
    suspend fun getAllBooks(): Response<List<Data>>
}

