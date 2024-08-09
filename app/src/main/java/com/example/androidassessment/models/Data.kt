package com.example.androidassessment.models

data class Data( // Renamed class name to Book for clarity
    val isbn: String, // Lowercase property names
    val notes: List<String>,
    val pages: Int,
    val publisher: String,
    val title: String,
    val year: Int,
    val createdAt: String,
    val handle: String,
    val id: Int,
    val villains: List<Villain>
)