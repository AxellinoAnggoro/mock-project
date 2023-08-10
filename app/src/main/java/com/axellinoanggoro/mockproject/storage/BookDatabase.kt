package com.axellinoanggoro.mockproject.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axellinoanggoro.mockproject.model.BookData

@Database(entities = [BookData :: class], version = 2)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao

    companion object{
        private var INSTANCE : BookDatabase? = null

        fun getInstance(context: Context) : BookDatabase? {
            if (INSTANCE == null){
                synchronized(BookData::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java, "Book.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return  INSTANCE
        }
    }
}