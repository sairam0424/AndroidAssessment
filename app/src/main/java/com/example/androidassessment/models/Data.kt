package com.example.androidassessment.models

data class Data(
    val id: Int,
    val year: Int,
//    val Title: String,
    val handle: String,
//    val Publisher: String,
    val isbn: String,
    val Pages: Int,
    val Notes: List<String>,
    val created_at: String,
    val publisher: String,
    val title: String,
    val villains: List<Villain>
)