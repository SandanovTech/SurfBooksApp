package com.example.surfbooksapp.data

import com.example.surfbooksapp.data.local.repository.LocalDataSource
import com.example.surfbooksapp.data.mapper.mapToDomainBook
import com.example.surfbooksapp.data.network.repository.RemoteDataSource
import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {
    override suspend fun getBooksByName(name: String): Book {
        return remoteDataSource.getBooksFromApiByName(name).mapToDomainBook()
    }

    override suspend fun addToFavourite(itemId: String, isFavourite: Boolean) {
        localDataSource.addToFavourite(itemId, isFavourite)
    }

    override suspend fun deleteFromFavourite(itemId: String) {
        localDataSource.deleteFromFavourite(itemId)
    }
}