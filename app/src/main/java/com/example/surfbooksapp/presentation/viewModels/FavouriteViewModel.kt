package com.example.surfbooksapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.domain.usecases.AddToFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.DeleteFromFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.GetAllBooksUseCase
import com.example.surfbooksapp.domain.usecases.GetFavouriteBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase
) : ViewModel(), BaseViewModel {

    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val books = _books.asStateFlow()

    private val _favouriteBooks: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val favouriteBooks = _favouriteBooks.asStateFlow()

    init {
        getAllBooks()
    }

    private fun getAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            _books.value = getAllBooksUseCase.invoke()
        }
    }

    override fun addToFavorite(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavouriteUseCase.invoke(book)
        }
    }

    override fun deleteFromFavourite(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromFavouriteUseCase.invoke(book)
        }
    }

    override fun getBooksByFavourite(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            _favouriteBooks.value = _books.value.filter { it.isFavourite }
        }
    }
}