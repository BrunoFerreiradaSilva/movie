package com.example.movie.ui.screens.movieDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movie.PATH_IMAGE
import com.example.movie.ui.components.FavoriteIcon

@Composable
fun MovieDetailsScreen(goToMovieList: () -> Unit) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading){
        MovieDetailsLoadingScreen()
    }

    if (state.showError){
        MovieDetailsErrorState()
    }

    if (state.showData){
        Column {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { goToMovieList() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back for movie list"
                        )
                    }
                },
                title = { Text(text = state.title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                actions = {
                    IconButton(onClick = {}) {
                        FavoriteIcon(isFavorite = state.isFavorite) { }
                    }
                }
            )
            AsyncImage(
                model = PATH_IMAGE + state.backgroundPath,
                contentDescription = "image poster detail",
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = state.title, fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp))


            Text(
                text = "Release Data: ${state.releaseDate}", modifier = Modifier.padding(
                    start = 8.dp
                )
            )

            Row() {
                state.genre.forEach { genre ->
                    Text(text = genre.name, modifier = Modifier.padding(start = 8.dp))
                }
            }

            Text(
                text = state.overView,
                Modifier.padding(8.dp),
                fontSize = 16.sp
            )
        }
    }
}