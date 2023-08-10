package com.axellinoanggoro.mockproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.mockproject.databinding.ActivityAddBookBinding
import com.axellinoanggoro.mockproject.databinding.ActivityEditBookBinding
import com.axellinoanggoro.mockproject.model.BookData
import com.axellinoanggoro.mockproject.storage.BookDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditBookActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBookBinding
    var bookDb: BookDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookDb = BookDatabase.getInstance(this)

        var fetchData = intent.getSerializableExtra("dataedit") as BookData

        binding.editbookId.setText(fetchData.id.toString())
        binding.editbookName.setText(fetchData.name)
        binding.editbookQuantity.setText(fetchData.quantity)
        binding.editbookSupplier.setText(fetchData.supplier)
        binding.editbookDate.setText(fetchData.date)


        binding.btnEditbook.setOnClickListener {
            editData()
        }
    }

    private fun editData() {
        var id = binding.editbookId.text.toString().toInt()
        var name = binding.editbookName.text.toString()
        var quantity = binding.editbookQuantity.text.toString()
        var supplier = binding.editbookSupplier.text.toString()
        var date = binding.editbookDate.text.toString()

        GlobalScope.async {
            val data = BookData(name = name, quantity = quantity, supplier = supplier, date = date)
            bookDb?.bookDao()?.updateBook((BookData(id, name, quantity, supplier, date)))
        }
        finish()
    }


    override fun onStart() {
        super.onStart()
    }
}