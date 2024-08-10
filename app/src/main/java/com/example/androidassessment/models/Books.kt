package com.example.androidassessment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Books", indices = [Index(value = ["id"], unique = true)])
data class Book(
    @PrimaryKey(autoGenerate = true)
    val bookId: Int = 0,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,

    @SerializedName("Title")
    @ColumnInfo(name = "Title")
    val title: String,

    @SerializedName("Publisher")
    @ColumnInfo(name = "Publisher")
    val publisher: String,

    @SerializedName("ISBN")
    @ColumnInfo(name = "ISBN")
    val isbn: String,

    @SerializedName("Year")
    @ColumnInfo(name = "Year")
    val year: String
)


