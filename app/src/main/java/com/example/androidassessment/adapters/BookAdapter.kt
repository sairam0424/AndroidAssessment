package com.example.androidassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassessment.R
import com.example.androidassessment.models.Books

class BookAdapter(
    private var bookList: MutableList<Books>,
    private val context: Context
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.tvId.text = "Id: ${book.id}"
        holder.tvTitle.text = "Title: ${book.title}"
        holder.tvPublisher.text = "Publisher: ${book.publisher}"
        holder.tvYear.text = "Year: ${book.year}"
        holder.tvISBN.text = "ISBN: ${book.isbn}"
    }

    fun getAllBooks(bookList: List<Books>) {
        this.bookList = bookList.toMutableList()
        notifyDataSetChanged() // Notify adapter about data change
    }

    override fun getItemCount(): Int = bookList.size

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvPublisher: TextView = itemView.findViewById(R.id.tvPublisher)
        val tvYear: TextView = itemView.findViewById(R.id.tvYear)
        val tvISBN: TextView = itemView.findViewById(R.id.tvISBN)
    }
}
