package com.example.surfbooksapp.presentation.viewModels

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.surfbooksapp.domain.model.Book

@Composable
inline fun <reified VM : ViewModel> customViewModel(): VM {
    return hiltViewModel()
}

interface BaseViewModel {
    fun addToFavorite(item: Book, isFavourite: Boolean)
    fun deleteFromFavourite(book: Book)
}