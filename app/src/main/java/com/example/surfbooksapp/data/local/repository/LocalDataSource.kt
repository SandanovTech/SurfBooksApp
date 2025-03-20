package com.example.surfbooksapp.data.local.repository

import com.example.surfbooksapp.data.local.model.BookEntity
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addToFavourite(book: Book, isFavourite: Boolean)
    suspend fun deleteFromFavourite(book: Book)
    fun getAllBooks(): Flow<List<BookEntity>>
}