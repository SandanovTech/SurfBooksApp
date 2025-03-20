package com.example.surfbooksapp.presentation.viewModels

import com.example.surfbooksapp.domain.model.Book

interface BaseViewModel {
    fun addToFavorite(book: Book, isFavourite: Boolean)
    fun deleteFromFavourite(book: Book)
}