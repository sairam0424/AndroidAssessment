package com.example.androidassessment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.androidassessment.adapters.BookAdapter
import com.example.androidassessment.models.Books
import com.example.androidassessment.network.Api
import com.example.androidassessment.repository.BookRepository
import com.example.androidassessment.ViewModel.BookViewModel
import com.example.androidassessment.adapter.BookAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var rvMain: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<Books>()
    private lateinit var bookRepository: BookRepository

    companion object {
        private const val URL_BASE = "https://stephen-king-api.onrender.com/api/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        rvMain.setHasFixedSize(true)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.itemAnimator = DefaultItemAnimator()

        bookRepository = BookRepository(application)
        bookAdapter = BookAdapter(bookList, this)

        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.getGetAllBooks.observe(this, Observer { books ->
            bookAdapter.getAllBooks(books)
            rvMain.adapter = bookAdapter
            Log.d("Main", "onChanged: $books")
        })

        networkRequest()
    }

    private fun networkRequest() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        val call = api.getAllBooks()
        call.enqueue(object : Callback<List<Books>> {
            override fun onResponse(call: Call<List<Books>>, response: Response<List<Books>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        bookRepository.insert(it)
                        Log.d("NetworkRequest", "Response: $it")
                    }
                }
            }

            override fun onFailure(call: Call<List<Books>>, t: Throwable) {
                Log.e("NetworkRequest", "Error: ${t.message}")
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
