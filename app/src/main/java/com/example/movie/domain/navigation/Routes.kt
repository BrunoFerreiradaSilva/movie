package com.example.movie.domain.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movie.FAVORITE_ROUTE
import com.example.movie.FAVORITE_TITLE
import com.example.movie.INITIAL_ROUTE
import com.example.movie.INITIAL_TITLE

sealed class Routes(val route: String, val title: String, val icon: ImageVector?) {
    object MovieList : Routes(INITIAL_ROUTE, INITIAL_TITLE, Icons.Default.Home)
    object Favorites : Routes(FAVORITE_ROUTE, FAVORITE_TITLE, Icons.Default.Star)
    object Details : Routes(FAVORITE_ROUTE, FAVORITE_TITLE, Icons.Default.Star)
}