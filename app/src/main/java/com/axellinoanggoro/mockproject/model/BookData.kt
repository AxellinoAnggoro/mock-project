package com.axellinoanggoro.mockproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var name : String,
    var quantity : String,
    var supplier : String,
    var date : String
) : java.io.Serializable
