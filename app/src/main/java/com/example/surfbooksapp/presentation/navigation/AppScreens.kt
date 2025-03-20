package com.example.surfbooksapp.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.surfbooksapp.presentation.screens.DetailsScreen
import com.example.surfbooksapp.presentation.screens.FavouriteScreen
import com.example.surfbooksapp.presentation.screens.MainScreen

sealed class AppScreens(val route: String) {
    data object MainScreen : AppScreens("mainScreen")
    data object FavouriteScreen : AppScreens("favouriteScreen")
    data object DetailsScreen : AppScreens("detailsScreen")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val items = NavigationItem.entries
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    var showBottomBar by remember { mutableStateOf(true) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect {
            val currentRoute = it.destination.route?.substringBefore('/')
            showBottomBar  = currentRoute != AppScreens.DetailsScreen.route
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.MainScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.MainScreen.route) {
                MainScreen(navController)
            }
            composable(
                "${AppScreens.DetailsScreen.route}/{bookId}",
                arguments = listOf(navArgument("bookId") {
                    type = NavType.StringType
                    nullable = true
                })
            ) { stackEntry ->
                stackEntry.arguments?.getString("bookId")?.let { itemId ->
                    DetailsScreen(navController, itemId)
                }
            }
            composable(AppScreens.FavouriteScreen.route) {
                FavouriteScreen(navController)
            }
        }
    }
}