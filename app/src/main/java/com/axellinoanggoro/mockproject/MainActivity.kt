package com.axellinoanggoro.mockproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.mockproject.databinding.ActivityMainBinding
import com.axellinoanggoro.mockproject.model.BookData
import com.axellinoanggoro.mockproject.storage.BookDatabase
import com.axellinoanggoro.mockproject.view.AddBookActivity
import com.axellinoanggoro.mockproject.view.adapter.BookAdapter
import com.axellinoanggoro.mockproject.viewmodel.BookViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    var bookDb : BookDatabase ?= null
    lateinit var bookAdapter : BookAdapter
    lateinit var bookVm : BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookDb = BookDatabase.getInstance(this)

        bookAdapter = BookAdapter(ArrayList())
        binding.rvNote.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.rvNote.adapter = bookAdapter

        bookVm = ViewModelProvider(this)[BookViewModel::class.java]
        bookVm.getAllBookObservers().observe(this, Observer {
            bookAdapter.setBookData(it as ArrayList<BookData>)
        })

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddBookActivity::class.java))
        }
    }

    fun getAllNote(){
        GlobalScope.launch {
            var data = bookDb?.bookDao()?.getDataBook()
            runOnUiThread{
                bookAdapter = BookAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = bookAdapter
            }
        }

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = bookDb?.bookDao()?.getDataBook()
            runOnUiThread {
                bookAdapter = BookAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
                binding.rvNote.adapter = bookAdapter
            }
        }
    }
}
