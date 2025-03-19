package com.example.surfbooksapp.data.local.repository

import com.example.surfbooksapp.data.local.BookDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val bookDao: BookDao) : LocalDataSource {
    override suspend fun addToFavourite(itemId: String, isFavourite: Boolean) {
        bookDao.addToFavourite(itemId, isFavourite)
    }

    override suspend fun deleteFromFavourite(itemId: String) {
        bookDao.deleteFromFavourite(itemId)
    }
}