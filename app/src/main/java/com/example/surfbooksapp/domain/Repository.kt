package com.example.surfbooksapp.domain

import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getBooksByName(name: String): List<Book>
    suspend fun getBookById(bookId : String) : Book
    suspend fun addToFavourite(book: Book, isFavourite: Boolean)
    suspend fun deleteFromFavourite(book: Book)
    suspend fun getFavouriteBooks(isFavourite: Boolean) : List<Book>
    fun getAllBooks() : Flow<List<Book>>
}