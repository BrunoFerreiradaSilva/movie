package com.example.movie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie.domain.navigation.Routes
import com.example.movie.presentation.ui.screens.movieDetails.MovieDetailsScreen
import com.example.movie.presentation.ui.screens.favoritemovies.FavoriteMoviesScreen
import com.example.movie.presentation.ui.screens.movies.MovieListScreen
import com.example.movie.presentation.ui.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            MovieTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.MovieList.route) {
        composable(
            route = Routes.MovieList.route
        ) {
            MovieListScreen(
                goToDetailsMovie = { navHostController.navigate("${Routes.Details.route}/${it}") },
                goToFavorites = { navHostController.navigate(Routes.Favorites.route) })
        }
        composable(
            route = "${Routes.Details.route}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        )
        {
            MovieDetailsScreen(goToMovieList = { navHostController.navigate(Routes.MovieList.route) })
        }
        composable(
            route = Routes.Favorites.route
        ) {
            FavoriteMoviesScreen(goToMovieList = { navHostController.navigate(Routes.MovieList.route) })
        }
    }
}