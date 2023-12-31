package com.example.movie.presentation.ui.screens.movieDetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movie.R
import com.example.movie.domain.helpers.PATH_IMAGE
import com.example.movie.presentation.ui.components.FavoriteIcon

@Composable
fun MovieDetailsScreen(goToMovieList: () -> Unit) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val state by viewModel.uiState.collectAsState()

    var visibility by remember { mutableStateOf(false) }

    if (state.isLoading) {
        MovieDetailsLoadingScreen()
    }

    if (state.showError) {
        MovieDetailsErrorState(viewModel::retry)
    }

    if (state.showData) {
        Column {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.purple_500),
                navigationIcon = {
                    IconButton(onClick = { goToMovieList() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.content_back_for_movie_list),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    state.movieDetail?.title?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White
                        )
                    }
                },
                actions = {
                    FavoriteIcon(
                        icon = if (state.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    ) {
                        if (state.isFavorite) {
                            state.movieDetail?.id?.let { viewModel.removeFavorite(it) }
                        } else {
                            state.movieDetail?.let { viewModel.favoriteMovie(it) }
                        }
                    }

                }
            )

            AsyncImage(
                model = PATH_IMAGE + state.movieDetail?.backgroundPath,
                contentDescription = stringResource(id = R.string.content_image_detail),
                modifier = Modifier.fillMaxWidth()
            )

            state.movieDetail?.title?.let {
                Text(
                    text = it,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            state.movieDetail?.let {
                Text(
                    text = stringResource(id = R.string.release_data_movie, it.releaseDate),
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            }


            Row {
                state.movieDetail?.genre?.forEach { genre ->
                    Text(text = genre.name, modifier = Modifier.padding(start = 8.dp))
                }
            }

            state.movieDetail?.overView?.let {
                Text(
                    text = it,
                    Modifier.padding(8.dp),
                    fontSize = 16.sp
                )
            }
        }

    }
}