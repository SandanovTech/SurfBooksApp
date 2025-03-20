package com.example.surfbooksapp.data.local.repository

import androidx.room.Entity
import com.example.surfbooksapp.data.local.model.BookEntity
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addToFavourite(book: BookEntity)
    suspend fun deleteFromFavourite(book: BookEntity)
    suspend fun insertAllBooks(books : List<BookEntity>)
    suspend fun getBookById(bookId: String) : BookEntity
    suspend fun getFavouriteBooks(isFavourite : Boolean) : List<BookEntity>
    suspend fun getAllBooks(): List<BookEntity>
}