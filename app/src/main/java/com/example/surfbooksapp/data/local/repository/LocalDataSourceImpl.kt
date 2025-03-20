package com.example.surfbooksapp.data.local.repository

import com.example.surfbooksapp.data.local.BookDao
import com.example.surfbooksapp.data.local.model.BookEntity
import com.example.surfbooksapp.data.mapper.mapToEntity
import com.example.surfbooksapp.data.mapper.mapToVolumeInfoLocal
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val bookDao: BookDao) : LocalDataSource {
    override suspend fun addToFavourite(book: Book, isFavourite: Boolean) {
        book.volumeInfo?.let {volumeInfo ->
            bookDao.addToFavourite(book.id, volumeInfo.mapToVolumeInfoLocal(), isFavourite)
        }
    }

    override suspend fun deleteFromFavourite(book: Book) {
        bookDao.deleteFromFavourite(book.mapToEntity())
    }

    override fun getAllBooks(): Flow<List<BookEntity>> {
        return bookDao.getAllBooks()
    }
}