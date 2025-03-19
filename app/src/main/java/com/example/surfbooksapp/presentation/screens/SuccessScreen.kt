package com.example.surfbooksapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.surfbooksapp.R
import com.example.surfbooksapp.domain.model.DomainItemsItem
import com.example.surfbooksapp.presentation.viewModels.MainViewModel

@Composable
fun SuccessScreen(innerPadding: PaddingValues) {
    val vm: MainViewModel = hiltViewModel()
    val booksState = vm.books.collectAsState()
    val books = booksState.value
    val items = books.flatMap { book ->
        book.items ?: emptyList()
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items) {
            it?.let { domainItemsItem ->
                CardElement(domainItemsItem = domainItemsItem)
            }
        }
    }
}

@Composable
fun CardElement(modifier: Modifier = Modifier, domainItemsItem: DomainItemsItem) {
    val vm: MainViewModel = hiltViewModel()
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(160.dp)
            .height(300.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            domainItemsItem.domainVolumeInfo?.imageLinks?.thumbnail
                                ?: R.drawable.ic_launcher_foreground
                        )
                        .build(),
                    contentDescription = "CardElement",
                    modifier = modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentScale = ContentScale.Crop,
                )
                domainItemsItem.domainVolumeInfo?.authors?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            text = it.joinToString(", "),
                            fontSize = 14.sp,
                            color = Color(0xFF8F8F8F),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                domainItemsItem.domainVolumeInfo?.title?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    domainItemsItem.id?.let {itemId ->
                        if (isFavorite) {
                            vm.addToFavorite(itemId, isFavorite)
                        } else {
                            vm.deleteFromFavourite(itemId)
                        }
                    }
                },
                modifier = modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    modifier = modifier.size(48.dp),
                    painter = if (isFavorite) painterResource(R.drawable.favourite_small) else painterResource(
                        R.drawable.favourite_active
                    ),
                    contentDescription = "Favorite",
                )
            }
        }
    }
}