package com.example.movie.ui.screens.favoritemovies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoriteMoviesScreen() {
    val viewModel = hiltViewModel<FavoriteMoviesViewModel>()
    val state by viewModel.uiState.collectAsState()

    Column() {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
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

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
//            items(state.listMovies) { item ->
//                Card(
//                    elevation = CardDefaults.cardElevation(4.dp),
//                    modifier = Modifier
//                        .padding(horizontal = 8.dp, vertical = 8.dp)
//                ) {
//                    AsyncImage(
//                        model = PATH_IMAGE + item.posterPath,
//                        contentDescription = "imageMovie",
//                        modifier = Modifier
//                            .size(200.dp)
//                            .clickable { goToDetailsMovie(item.id) },
//                        contentScale = ContentScale.FillBounds
//                    )
//                    Row {
//                        androidx.compose.material3.Text(
//                            text = item.title,
//                            modifier = Modifier
//                                .padding(6.dp)
//                                .width(126.dp),
//                            textAlign = TextAlign.Start,
//                            fontSize = 14.sp,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                        FavoriteIcon(isFavorite = state.isFavorite) {
//                            if (state.isFavorite) {
//                                viewModel.removeFavorite()
//                            } else {
//                                viewModel.favoriteMovie()
//                            }
//                        }
//                    }
//                }
//            }
        }
    }

}