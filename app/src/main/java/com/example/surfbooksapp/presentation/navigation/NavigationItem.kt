package com.example.surfbooksapp.presentation.navigation

import com.example.surfbooksapp.R

enum class NavigationItem(
    val title: String,
    val icon: Int,
    val route: String
) {
    SEARCH("Search", R.drawable.search_icon, AppScreens.MainScreen.route),
    FAVOURITE("Favourite", R.drawable.favoruite_default, AppScreens.FavouriteScreen.route)
}