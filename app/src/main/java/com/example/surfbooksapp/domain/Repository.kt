package com.example.surfbooksapp.domain

import com.example.surfbooksapp.domain.model.Book

interface Repository {
    suspend fun getBooksByName(name: String): Book
    suspend fun addToFavourite(itemId: String, isFavourite: Boolean)
    suspend fun deleteFromFavourite(itemId: String)
}