package com.example.androidassessment

import com.example.androidassessment.models.Users
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/posts")
    suspend fun getAllUsers():Response<Users>
}
