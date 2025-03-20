package com.example.surfbooksapp.domain.usecases

import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(): List<Book> = repository.getAllBooks()
}