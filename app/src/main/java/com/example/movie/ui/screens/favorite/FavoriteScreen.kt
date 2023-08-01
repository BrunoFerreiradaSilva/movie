package com.example.movie.ui.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movie.PATH_IMAGE
import com.example.movie.ui.components.FavoriteIcon

@Composable
fun FavoriteScreen() {
    val viewModel = hiltViewModel<FavoriteViewModel>()
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