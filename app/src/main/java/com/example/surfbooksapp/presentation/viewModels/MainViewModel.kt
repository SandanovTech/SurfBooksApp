package com.example.surfbooksapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.domain.usecases.AddToFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.DeleteFromFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.GetBooksByNameUseCase
import com.example.surfbooksapp.domain.usecases.GetFavouriteBooksUseCase
import com.example.surfbooksapp.presentation.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBooksByNameUseCase: GetBooksByNameUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val getFavouriteBooksUseCase: GetFavouriteBooksUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
) : ViewModel(), BaseViewModel {

    private val _state: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val state = _state.asStateFlow()

    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val books = _books.asStateFlow()

    init {
        _state.value = MainScreenState.Initial
    }

    fun clear() {
        _state.value = MainScreenState.Initial
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
            _books.value = getFavouriteBooksUseCase.invoke(book.isFavourite)
        }
    }

    fun getBooksByName(name: String) {
        _state.value = MainScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _books.value = getBooksByNameUseCase.invoke(name)
            withContext(Dispatchers.Main) { // Обновление UI в главном потоке
                _state.value = MainScreenState.Success(_books.value)
            }
        }
    }

}