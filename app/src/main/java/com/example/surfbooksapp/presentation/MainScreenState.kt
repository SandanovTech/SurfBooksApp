package com.example.surfbooksapp.presentation

import com.example.surfbooksapp.domain.model.Book

sealed interface MainScreenState {
    data object Loading : MainScreenState
    data object Initial : MainScreenState
    data object Error : MainScreenState
    data class Success(private val books : List<Book>) : MainScreenState
}