package com.example.surfbooksapp.domain

import com.example.surfbooksapp.domain.model.Book

interface Repository {
    suspend fun getBooksByName(name: String): List<Book>
}