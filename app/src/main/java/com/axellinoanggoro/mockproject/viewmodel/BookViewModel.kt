package com.axellinoanggoro.mockproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.axellinoanggoro.mockproject.model.BookData
import com.axellinoanggoro.mockproject.storage.BookDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookViewModel(app : Application) : AndroidViewModel(app) {
    var showBook : MutableLiveData<List<BookData>> = MutableLiveData()

    init {
        getAllBook()
    }

    fun getAllBookObservers() : MutableLiveData<List<BookData>>{
        return showBook
    }

    fun getAllBook() {
        GlobalScope.launch {
            val userDao = BookDatabase.getInstance(getApplication())!!.bookDao()
            val listBook = userDao.getDataBook()
            showBook.postValue(listBook)
        }
    }
}