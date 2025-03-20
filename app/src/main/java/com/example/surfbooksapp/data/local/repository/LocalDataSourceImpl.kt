package com.example.surfbooksapp.data.local.repository

import com.example.surfbooksapp.data.local.BookDao
import com.example.surfbooksapp.data.local.model.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val bookDao: BookDao) : LocalDataSource {
    override suspend fun addToFavourite(book: BookEntity) {
        book.isFavourite = true
        bookDao.addToFavourite(book)
    }

    override suspend fun deleteFromFavourite(book: BookEntity) {
        book.isFavourite = false
        bookDao.deleteFromFavourite(book)
    }

    override suspend fun insertAllBooks(books: List<BookEntity>) {
        return bookDao.insertAllBooks(books)
    }

    override suspend fun getBookById(bookId: String): BookEntity {
        return bookDao.getBookById(bookId)
    }

    override suspend fun getFavouriteBooks(isFavourite : Boolean): List<BookEntity> {
        val isFavouriteInt = if (isFavourite) 1 else 0
        return bookDao.getFavouriteBooks(isFavouriteInt)
    }

    override suspend fun getAllBooks(): List<BookEntity> {
        return bookDao.getAllBooks()
    }
}