package com.example.surfbooksapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surfbooksapp.domain.usecases.GetBooksByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBooksByNameUseCase: GetBooksByNameUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val state = _state.asStateFlow()

    fun getBooksByName(name: String) {
        _state.value = MainScreenState.Loading
        getBooksByNameUseCase.getBooksByNameUseCase(name).onEach { books ->
            _state.value = MainScreenState.Success(books)
        }.launchIn(viewModelScope)
    }
}