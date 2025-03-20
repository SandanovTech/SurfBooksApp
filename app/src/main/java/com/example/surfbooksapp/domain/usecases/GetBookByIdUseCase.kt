package com.example.surfbooksapp.domain.usecases

import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(bookId: String): Book {
        return repository.getBookById(bookId)
    }
}