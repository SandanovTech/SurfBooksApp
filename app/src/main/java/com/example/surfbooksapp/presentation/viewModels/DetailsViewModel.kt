package com.example.surfbooksapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.domain.usecases.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
) : ViewModel() {

    private val _book : MutableStateFlow<Book?> = MutableStateFlow(null)
    val book = _book.asStateFlow()

    fun getBookById(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _book.value = getBookByIdUseCase.invoke(bookId)
        }
    }
}