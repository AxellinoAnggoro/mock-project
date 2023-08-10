package com.axellinoanggoro.mockproject.storage

import androidx.room.*
import com.axellinoanggoro.mockproject.model.BookData

@Dao
interface BookDao {
    @Insert
    fun insertBook(bookData: BookData)

    //    @Query("SELECT * FROM NoteData ORDER BY id DESC")
    @Query("SELECT * FROM BookData")
    fun getDataBook() : List<BookData>

    @Delete
    fun deleteBook(bookData: BookData)

    @Update
    fun updateBook(bookData: BookData)
}