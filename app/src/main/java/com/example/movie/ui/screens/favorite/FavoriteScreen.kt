package com.example.movie.ui.screens.favorite

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable

@Composable
fun FavoriteScreen() {
    TopAppBar(
        title = { Text(text = "Favorites") },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search favorite movie"
                )
            }
        }
    )
}