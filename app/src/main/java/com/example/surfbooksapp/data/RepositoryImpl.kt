package com.example.surfbooksapp.data

import com.example.surfbooksapp.data.local.repository.LocalDataSource
import com.example.surfbooksapp.data.mapper.mapToDomainListBook
import com.example.surfbooksapp.data.mapper.mapToDomainListFlowBook
import com.example.surfbooksapp.data.network.repository.RemoteDataSource
import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override suspend fun getBooksByName(name: String): List<Book> {
        return remoteDataSource.getBooksFromApiByName(name).mapToDomainListBook()
    }

    override suspend fun addToFavourite(book: Book, isFavourite: Boolean) {
        localDataSource.addToFavourite(book, isFavourite)
    }

    override suspend fun deleteFromFavourite(book: Book) {
        localDataSource.deleteFromFavourite(book)
    }

    override fun getAllBooks(): Flow<List<Book>> {
        return localDataSource.getAllBooks().mapToDomainListFlowBook()
    }

}