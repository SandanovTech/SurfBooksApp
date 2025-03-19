package com.example.surfbooksapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.domain.model.DomainItemsItem
import com.example.surfbooksapp.domain.usecases.AddToFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.DeleteFromFavouriteUseCase
import com.example.surfbooksapp.domain.usecases.GetBooksByNameUseCase
import com.example.surfbooksapp.presentation.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBooksByNameUseCase: GetBooksByNameUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val state = _state.asStateFlow()

    private val _books : MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val books = _books.asStateFlow()

    init {
        _state.value = MainScreenState.Initial
    }

    fun clear() {
        _state.value = MainScreenState.Initial
    }

    fun addToFavorite(itemId: String, isFavourite : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavouriteUseCase.invoke(itemId ,isFavourite)
        }
    }

    fun deleteFromFavourite(itemId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromFavouriteUseCase.invoke(itemId)
        }
    }

    fun getBooksByName(name: String) {
        _state.value = MainScreenState.Loading
        getBooksByNameUseCase.invoke(name).onEach { books ->
            for (items in books) {
                if (items.items.isNullOrEmpty()) {
                    _state.value = MainScreenState.Error
                } else {
                    _books.value = books
                    _state.value = MainScreenState.Success(books)
                }
            }
        }.launchIn(viewModelScope)
    }

}