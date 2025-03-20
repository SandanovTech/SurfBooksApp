package com.example.surfbooksapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.domain.usecases.AddToFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.DeleteFromFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
) : ViewModel(), BaseViewModel{

    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val books = _books.asStateFlow()

    init {
        getBooksByFavourite()
    }
    override fun addToFavorite(book: Book, isFavourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavouriteUseCase.invoke(book, isFavourite)
        }
    }

    override fun deleteFromFavourite(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromFavouriteUseCase.invoke(book)
        }
    }

    private fun getBooksByFavourite() {
        getAllBooksUseCase.invoke().onEach {
            _books.value = it
        }.launchIn(viewModelScope)
    }
}