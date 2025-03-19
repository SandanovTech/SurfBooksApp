package com.example.surfbooksapp.data.network.repository

import com.example.surfbooksapp.data.network.model.BookResponse

interface RemoteDataSource {
    suspend fun getBooksFromApiByName(name : String): BookResponse
}