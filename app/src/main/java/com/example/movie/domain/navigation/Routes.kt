package com.example.movie.domain.navigation

import com.example.movie.domain.helpers.DETAIL_ROUTE
import com.example.movie.domain.helpers.FAVORITE_ROUTE
import com.example.movie.domain.helpers.INITIAL_ROUTE

sealed class Routes(val route: String) {
    object MovieList : Routes(INITIAL_ROUTE)
    object Favorites : Routes(FAVORITE_ROUTE)
    object Details : Routes(DETAIL_ROUTE)
}