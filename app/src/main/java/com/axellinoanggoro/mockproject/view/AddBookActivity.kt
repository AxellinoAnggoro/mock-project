package com.axellinoanggoro.mockproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.mockproject.databinding.ActivityAddBookBinding
import com.axellinoanggoro.mockproject.model.BookData
import com.axellinoanggoro.mockproject.storage.BookDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddBookActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBookBinding
    var bookDb : BookDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookDb = BookDatabase.getInstance(this)

        binding.btnSavebook.setOnClickListener {
            addData()
        }
    }

    private fun addData() {
        GlobalScope.async {
            var name = binding.bookName.text.toString()
            var quantity = binding.bookQuantity.text.toString()
            var supplier = binding.bookSupplier.text.toString()
            var date = binding.bookDate.text.toString()

            val data = BookData(name = name, quantity = quantity, supplier = supplier, date = date)
            bookDb?.bookDao()?.insertBook(data)
        }
        finish()
    }

}