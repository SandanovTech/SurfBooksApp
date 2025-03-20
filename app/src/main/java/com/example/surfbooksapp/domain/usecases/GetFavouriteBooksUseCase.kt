package com.example.surfbooksapp.domain.usecases

import com.example.surfbooksapp.domain.Repository
import com.example.surfbooksapp.domain.model.Book
import javax.inject.Inject

class GetFavouriteBooksUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(isFavourite : Boolean): List<Book> {
        return repository.getFavouriteBooks(isFavourite)
    }
}