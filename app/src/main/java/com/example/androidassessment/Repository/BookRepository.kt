package com.example.androidassessment.repository

import android.app.Application
import androidx.lifecycle.LiveData
//import com.example.androidassessment.Dao.BookDao
import com.example.androidassessment.database.BookDatabase
import com.example.androidassessment.models.Books
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookRepository(application: Application) {

    private val bookDatabase: BookDatabase = BookDatabase.getInstance(application)
    private val getAllBooks: LiveData<List<Books>>

    init {
        getAllBooks = bookDatabase.bookDao().loadAll()
    }

    fun insert(bookList: List<Books>) {
        CoroutineScope(Dispatchers.IO).launch {
            insertBooks(bookList)
        }
    }

    fun getAllBooks(): LiveData<List<Books>> {
        return getAllBooks
    }

    private suspend fun insertBooks(bookList: List<Books>) {
        withContext(Dispatchers.IO) {
            bookDatabase.bookDao().insert(bookList)
        }
    }
}
