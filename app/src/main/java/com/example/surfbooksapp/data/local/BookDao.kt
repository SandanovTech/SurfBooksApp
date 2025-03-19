package com.example.surfbooksapp.data.local

import androidx.room.Dao
import com.example.surfbooksapp.data.local.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    fun getBookByName() : Flow<List<BookEntity>>
}