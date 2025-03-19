package com.example.surfbooksapp.domain.usecases

import com.example.surfbooksapp.domain.Repository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(itemId: String, isFavourite: Boolean) {
        repository.addToFavourite(itemId, isFavourite)
    }
}