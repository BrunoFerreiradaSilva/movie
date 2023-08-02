package com.example.movie.ui.screens.movies


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
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movie.PATH_IMAGE
import com.example.movie.R
import com.example.movie.ui.components.FavoriteIcon


@Composable
fun MovieListScreen(goToDetailsMovie: (Int) -> Unit, goToFavorites: () -> Unit) {
    val viewModel = hiltViewModel<MovieListViewModel>()
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        LoadingState()
    }

    if (state.showError) {
        ErrorState()
    }

    if (state.showData) {
        Column() {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { androidx.compose.material.Text(text = "Movie") },
                actions = {
                    IconButton(onClick = { goToFavorites() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            contentDescription = "Go to favorite screen"
                        )
                    }
                }
            )
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.data) { item ->
                    Card(
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        AsyncImage(
                            model = PATH_IMAGE + item.backdropPath,
                            contentDescription = "imageMovie",
                            modifier = Modifier
                                .size(200.dp)
                                .clickable { goToDetailsMovie(item.id) },
                            contentScale = ContentScale.FillBounds
                        )
                        Row {
                            Text(
                                text = item.title,
                                modifier = Modifier
                                    .padding(6.dp)
                                    .width(126.dp),
                                textAlign = TextAlign.Start,
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                           FavoriteIcon(isFavorite = item.isFavorite) {
                                if (item.isFavorite){
                                    viewModel.removeFavorite(item.id)
                                }else{
                                    viewModel.favoriteMovie(item)
                                }
                           }
                        }
                    }
                }
            }
        }

    }
}

