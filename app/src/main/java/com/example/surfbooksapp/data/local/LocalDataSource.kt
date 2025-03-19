package com.example.surfbooksapp.data.local

import com.example.surfbooksapp.data.local.model.BookEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getBooksFromCachesByName(name : String) : Flow<List<BookEntity>>
}