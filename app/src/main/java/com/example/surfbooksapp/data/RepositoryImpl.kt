package com.example.surfbooksapp.data

import android.util.Log
import com.example.surfbooksapp.data.local.repository.LocalDataSource
import com.example.surfbooksapp.data.mapper.mapToDomain
import com.example.surfbooksapp.data.mapper.mapToDomainListBook
import com.example.surfbooksapp.data.mapper.mapToDomainListFlowBook
import com.example.surfbooksapp.data.mapper.mapToEntity
import com.example.surfbooksapp.data.network.repository.RemoteDataSource
import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override suspend fun getBooksByName(name: String): List<Book> {
        return remoteDataSource.getBooksFromApiByName(name).mapToDomainListBook()
            .also { localDataSource.insertAllBooks(it.mapToEntity()) }
    }

    override suspend fun getBookById(bookId: String): Book {
        return localDataSource.getBookById(bookId).mapToDomain()
    }

    override suspend fun addToFavourite(book: Book) {
        localDataSource.addToFavourite(book.mapToEntity())
    }

    override suspend fun deleteFromFavourite(book: Book) {
        localDataSource.deleteFromFavourite(book.mapToEntity())
    }

    override suspend fun getFavouriteBooks(isFavourite : Boolean): List<Book> {
        return localDataSource.getFavouriteBooks(isFavourite).mapToDomainListBook()
    }

    override suspend fun getAllBooks(): List<Book> {
        return localDataSource.getAllBooks().mapToDomainListBook()
    }

}