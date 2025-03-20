package com.example.surfbooksapp.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.surfbooksapp.R
import com.example.surfbooksapp.domain.model.Book
import com.example.surfbooksapp.presentation.navigation.AppScreens
import com.example.surfbooksapp.presentation.viewModels.BaseViewModel

@Composable
fun <viewModel : BaseViewModel> CardElement(
    modifier: Modifier = Modifier,
    book: Book,
    vm: viewModel,
    navController: NavHostController
) {
    var isFavorite by rememberSaveable { mutableStateOf(book.isFavourite) }
    if (book.isFavourite) {
        vm.getBooksByFavourite(book)
    }
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(300.dp)
            .clickable {
                navController.navigate("${AppScreens.DetailsScreen.route}/${book.id}")
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            book.volumeInfo?.imageLinks?.thumbnail
                                ?: R.drawable.ic_launcher_foreground
                        )
                        .build(),
                    contentDescription = "CardElement",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentScale = ContentScale.Crop,
                )
                book.volumeInfo?.authors?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            text = it.joinToString(", "),
                            fontSize = 14.sp,
                            color = Color(0xFF8F8F8F),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                book.volumeInfo?.title?.let {
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
                    book.isFavourite = isFavorite
                    if (book.isFavourite) {
                        vm.addToFavorite(book)
                    } else {
                        vm.deleteFromFavourite(book)
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = if (isFavorite) {
                        painterResource(R.drawable.favourite_active)
                    } else {
                        painterResource(R.drawable.favoruite_default)
                    },
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}