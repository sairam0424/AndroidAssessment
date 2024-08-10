package com.example.androidassessment.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidassessment.model.Books

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(books: List<Books>)

    @Query("SELECT * FROM Books")
    fun loadAll(): LiveData<List<Books>>

    @Query("DELETE FROM Books")
    fun deleteAll()
}
