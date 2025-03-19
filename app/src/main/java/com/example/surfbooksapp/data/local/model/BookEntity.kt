package com.example.surfbooksapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id : String,
    val isFavourite : Boolean,
)
