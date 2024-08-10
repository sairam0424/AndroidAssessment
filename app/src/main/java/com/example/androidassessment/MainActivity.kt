package com.example.androidassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassessment.adapters.BookAdapter
import com.example.androidassessment.databinding.ActivityMainBinding
import com.example.androidassessment.models.Data
import com.example.androidassessment.utils.ApiClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//import kotlinx.coroutines.DelicateCoroutinesApi
//import kotlinx.coroutines.GlobalScope
//import android.widget.Toast
//import retrofit2.HttpException
//import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate: Starting MainActivity")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MainActivity", "onCreate: View binding completed")

        // Initialize RecyclerView and Adapter with an empty list
        bookAdapter = BookAdapter(emptyList())
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bookAdapter
            Log.d("MainActivity", "RecyclerView layoutManager and Adapter set")
        }

        // Fetch data from API in background thread
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("MainActivity", "Starting data fetch from API")
            val response = try {
                ApiClient.api.getAllBooks().also {
                    Log.d("MainActivity", "API response received")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching books: ${e.message}")
                withContext(Dispatchers.Main) {
                    Snackbar.make(binding.root, "Error fetching data: ${e.message}", Snackbar.LENGTH_LONG).show()
                }
                return@launch
            }

            withContext(Dispatchers.Main) {
                Log.d("MainActivity", "Processing API response on the main thread")
                if (response.isSuccessful && response.body() != null) {
                    val books: List<Data> = response.body()!!.data
                    Log.d("MainActivity", "Books fetched: $books")

                    // Update the adapter's data
                    bookAdapter = BookAdapter(books)
                    binding.rvMain.adapter = bookAdapter
                    bookAdapter.notifyDataSetChanged()
                    Log.d("MainActivity", "Adapter data updated")
                } else {
                    Snackbar.make(binding.root, "Failed to load books.", Snackbar.LENGTH_LONG).show()
                    Log.e("MainActivity", "Response error: ${response.message()}")
                }
            }
        }

        Log.d("MainActivity", "onCreate: Completed")
    }
}
