package com.example.movie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movie.navigation.Routes
import com.example.movie.ui.loading.LoadingScreen
import com.example.movie.ui.screens.MovieListScreen
import com.example.movie.ui.theme.MovieTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()


            showBottomBar = when (navBackStackEntry?.destination?.route) {
                Routes.MovieList.route -> true
                else -> false
            }

            MovieTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        if (showBottomBar) BottomBar(navHostController = navHostController)
                    }) {
                        BottomNavGraph(navHostController = navHostController)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    val isLoading = remember {
        mutableStateOf(true)
    }
    NavHost(navController = navHostController, startDestination = Routes.MovieList.route) {
        composable(
            route = Routes.MovieList.route
        ) {
            if (isLoading.value){
                LoadingScreen()
                LaunchedEffect(Unit) {
                    delay(2000)
                    isLoading.value = false
                }
            }else{
                MovieListScreen(goToDetailsMovie = {})
            }
        }
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val routes = listOf(
        Routes.MovieList,
    )

    val navBackStackEntry = navHostController.currentBackStackEntry
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        routes.forEach { screen ->
            AddItem(
                routes = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    routes: Routes,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    BottomNavigationItem(modifier = Modifier.background(Color.Black),
        label = { Text(text = routes.title) },
        icon = {
            routes.icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "Navigation Icon"
                )
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == routes.route
        } == true,
        onClick = { navHostController.navigate(routes.route) }
    )

}