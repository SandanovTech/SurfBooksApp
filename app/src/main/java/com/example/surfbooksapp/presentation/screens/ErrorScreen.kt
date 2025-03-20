package com.example.surfbooksapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.surfbooksapp.presentation.viewModels.MainViewModel

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, query: String) {
    val vm: MainViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "По вашему запросу ничего не найдено", textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            fontSize = 18.sp
        )
        Button(onClick = {
            vm.getBooksByName(query)
        }) {
            Text(
                text = "Ошибка выполения запроса,\nпопробуйте повторить",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}
