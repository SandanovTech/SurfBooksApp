package com.example.surfbooksapp.data.local.repository

import com.example.surfbooksapp.data.local.model.BookEntity
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addToFavourite(book: Book, isFavourite: Boolean)
    suspend fun deleteFromFavourite(book: Book)
    suspend fun insertAllBooks(books : List<BookEntity>)
    suspend fun getBookById(bookId: String) : BookEntity
    suspend fun getFavouriteBooks(isFavourite: Boolean) : List<BookEntity>
    fun getAllBooks(): Flow<List<BookEntity>>
}