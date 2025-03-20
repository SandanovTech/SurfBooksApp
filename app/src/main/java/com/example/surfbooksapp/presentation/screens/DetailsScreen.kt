package com.example.surfbooksapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.surfbooksapp.R
import com.example.surfbooksapp.presentation.viewModels.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, itemId: String) {

    val scaffoldState = rememberBottomSheetScaffoldState()

    val vm: DetailsViewModel = hiltViewModel()
    LaunchedEffect(itemId) {
        vm.getBookById(itemId)
    }
    val book by vm.book.collectAsState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Описание", fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(4.dp)
                )
                book?.let {
                    Text(
                        text = it.volumeInfo?.description ?: "Description not available",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        },
        topBar = {
            TopAppBar(title = {
                Row(modifier = Modifier.fillMaxSize()) {

                }
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "ArrowBack")
                }
            }, actions = {
                IconButton(onClick = { /* Действие для второй иконки */ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        book?.volumeInfo?.imageLinks?.thumbnail
                            ?: R.drawable.ic_launcher_foreground
                    )
                    .build(),
                contentDescription = "CardElement",
                modifier = Modifier
                    .width(230.dp)
                    .height(300.dp),
                contentScale = ContentScale.Crop,
            )
            book?.let {
                Text(
                    text = it.volumeInfo?.authors?.joinToString(", ") ?: "Author not available",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = it.volumeInfo?.title ?: "Title not available",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = extractYearFromDate(it.volumeInfo?.publishedDate),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

private fun extractYearFromDate(dateString: String?): String {
    return if (!dateString.isNullOrEmpty() && dateString.length >= 4) {
        dateString.substring(0, 4)
    } else {
        "Book was wrote..."
    }
}