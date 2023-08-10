package com.axellinoanggoro.mockproject.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.mockproject.MainActivity
import com.axellinoanggoro.mockproject.databinding.ItemBookBinding
import com.axellinoanggoro.mockproject.model.BookData
import com.axellinoanggoro.mockproject.storage.BookDatabase
import com.axellinoanggoro.mockproject.view.EditBookActivity
import com.axellinoanggoro.mockproject.viewmodel.BookViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class BookAdapter(var listBook : List<BookData>) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var bookDb : BookDatabase ?= null

    class ViewHolder(var binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        var view = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        holder.binding.bookId.text = listBook[position].id.toString()
        holder.binding.bookName.text = listBook[position].name
        holder.binding.bookQuantity.text = listBook[position].quantity
        holder.binding.bookSupplier.text = listBook[position].supplier
        holder.binding.bookDate.text = listBook[position].date

        holder.binding.btnDeleteNote.setOnClickListener {
            bookDb = BookDatabase.getInstance(it.context)

            //delete dan update
            GlobalScope.async {
                val del = bookDb?.bookDao()?.deleteBook(listBook[position])
                (holder.itemView.context as MainActivity).runOnUiThread{
                    (holder.itemView.context as MainActivity).getAllNote()
                }

            }
        }

        holder.binding.btnEditNote.setOnClickListener {
            var move = Intent(it.context, EditBookActivity::class.java )
            move.putExtra("dataedit", listBook[position])
            it.context.startActivity(move)
        }

    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    fun setBookData(listBook : ArrayList<BookData>){
        this.listBook = listBook
    }

}