package com.example.surfbooksapp.domain.usecases

import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import javax.inject.Inject

class GetBooksByNameUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(name: String): List<Book> {
        return repository.getBooksByName(name)
    }
}