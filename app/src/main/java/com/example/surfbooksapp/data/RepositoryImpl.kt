package com.example.surfbooksapp.data

import com.example.surfbooksapp.data.network.repository.RemoteDataSource
import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
//    private val localDataSource : LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {
    override suspend fun getBooksByName(name: String) : List<Book> {
        remoteDataSource.getBooksFromApiByName(name)
        return emptyList()
    }
}