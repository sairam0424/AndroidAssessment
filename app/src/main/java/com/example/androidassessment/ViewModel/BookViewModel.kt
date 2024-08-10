package com.example.androidassessment.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.androidassessment.models.Books
import com.example.androidassessment.repository.BookRepository

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository: BookRepository = BookRepository(application)
    val getGetAllBooks: LiveData<List<Books>> = bookRepository.getAllBooks()

    fun insert(bookList: List<Books>) {
        bookRepository.insert(bookList)
    }

}
