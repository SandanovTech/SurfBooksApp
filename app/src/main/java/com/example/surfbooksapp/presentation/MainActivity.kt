package com.example.surfbooksapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.surfbooksapp.R
import com.example.surfbooksapp.presentation.screens.ErrorScreen
import com.example.surfbooksapp.presentation.screens.InitialScreen
import com.example.surfbooksapp.presentation.screens.LoadingScreen
import com.example.surfbooksapp.presentation.screens.SuccessScreen
import com.example.surfbooksapp.presentation.theme.SurfBooksAppTheme
import com.example.surfbooksapp.presentation.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SurfBooksAppTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    val vm: MainViewModel = hiltViewModel()
    val state by vm.state.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }

    val items = listOf("Search"," Favorite")
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val selectedIcons = listOf(R.drawable.search_active, R.drawable.favourite_active)
    val unSelectedIcons = listOf(R.drawable.search_icon, R.drawable.favoruite_default)

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
    }, bottomBar = {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(icon = {
                    Icon(
                        painter = painterResource(if (selectedItem == index) selectedIcons[index] else unSelectedIcons[index]),
                        contentDescription = null
                    )
                },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }
    }) { innerPadding ->
        when (state) {
            MainScreenState.Error -> {
                ErrorScreen(innerPadding, query)
            }

            MainScreenState.Loading -> {
                LoadingScreen(innerPadding)
            }

            is MainScreenState.Success -> {
                SuccessScreen(innerPadding)
            }

            MainScreenState.Initial -> {
                InitialScreen(innerPadding)
            }
        }
    }
}

@Composable
fun SimpleSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            placeholder = { Text("Поиск...") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = "Поиск"
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onSearch()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Очистка"
                    )
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.run {
                colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            }
        )
    }
}