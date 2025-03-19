package com.example.surfbooksapp.domain.usecases

import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBooksByNameUseCase @Inject constructor(private val repository: Repository) {
    fun getBooksByNameUseCase(name: String) : Flow<List<Book>> = flow {
       val books = repository.getBooksByName(name)
        emit(books)
    }
}