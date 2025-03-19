package com.example.surfbooksapp.data.local.repository

interface LocalDataSource {
    suspend fun addToFavourite(itemId: String, isFavourite: Boolean)
    suspend fun deleteFromFavourite(itemId: String)
}