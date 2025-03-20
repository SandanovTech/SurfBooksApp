package com.example.surfbooksapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.surfbooksapp.presentation.utils.CardElement
import com.example.surfbooksapp.presentation.viewModels.FavouriteViewModel

@Composable
fun FavouriteScreen(navController: NavHostController) {
    val vm: FavouriteViewModel = hiltViewModel()
    val favouriteBooks by vm.favouriteBooks.collectAsState()
    val books by vm.books.collectAsState()
    Log.d("DdddddddddddddddddD","Favourite size: ${favouriteBooks.size}")
    Log.d("DdddddddddddddddddD","Books size: ${books.size}")
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(favouriteBooks) {
            CardElement(book = it, vm = vm, navController = navController)
        }
    }
}
