package com.example.surfbooksapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.surfbooksapp.presentation.MainScreenState
import com.example.surfbooksapp.presentation.utils.SimpleSearchBar
import com.example.surfbooksapp.presentation.navigation.NavigationItem
import com.example.surfbooksapp.presentation.viewModels.MainViewModel

@Composable
fun MainScreen(navController: NavHostController) {
    val vm: MainViewModel = hiltViewModel()
    val state by vm.state.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }

    Scaffold(topBar = {
        SimpleSearchBar(
            query,
            onQueryChange = { newQuery ->
                query = newQuery
                vm.getBooksByName(newQuery)
            },
            onSearch = {
                query = ""
                vm.clear()
            },
        )
    }) { paddingValues ->
        when (state) {
            MainScreenState.Error -> {
                ErrorScreen(
                    modifier = Modifier.padding(paddingValues),
                    query
                )
            }

            MainScreenState.Loading -> {
                LoadingScreen(modifier = Modifier.padding(paddingValues))
            }

            is MainScreenState.Success -> {
                SuccessScreen(navController, modifier = Modifier.padding(paddingValues))
            }

            MainScreenState.Initial -> {
                InitialScreen(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}