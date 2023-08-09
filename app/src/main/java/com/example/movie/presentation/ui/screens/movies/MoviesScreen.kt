package com.example.movie.presentation.ui.screens.movies


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movie.domain.helpers.PATH_IMAGE
import com.example.movie.R
import com.example.movie.presentation.ui.components.FavoriteIcon
import kotlinx.coroutines.flow.distinctUntilChanged


@Composable
fun MovieListScreen(goToDetailsMovie: (Int) -> Unit, goToFavorites: () -> Unit) {
    val viewModel = hiltViewModel<MovieListViewModel>()
    val state by viewModel.uiState.collectAsState()

    val lazyListState = rememberLazyListState()

    if (state.isLoading) {
        LoadingState()
    }

    if (state.showError) {
        MoviesErrorState(viewModel::retry)
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
                                if (item.isFavorite) {
                                    viewModel.removeFavorite(item.id)
                                } else {
                                    viewModel.favoriteMovie(item)
                                }
                            }
                        }
                    }
                }
                item(span = { GridItemSpan(2) }) {
                    Pagination(listState = lazyListState) {
                        viewModel.getMoreMovies()
                    }
                    MoreMovieState(isError = state.errorNextPage) {
                        viewModel.getMoreMovies()
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorGetNextPage(action: () -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { action() }
        .fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = "Error",
                colorFilter = ColorFilter.tint(
                    Color.Red
                )
            )
            Text(text = "Não foi possivel carregar")
            OutlinedButton(onClick = { action() }, modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "Retry")
            }
        }

    }
}

@Composable
fun Pagination(
    listState: LazyListState,
    buffer: Int = 2,
    action: () -> Unit,

    ) {
    var lastTotalItems = -1
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex =
                (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            val loadMore =
                lastVisibleItemIndex > (totalItemsNumber - buffer) && (lastTotalItems != totalItemsNumber)

            loadMore
        }
    }
    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                if (it) {
                    lastTotalItems = listState.layoutInfo.totalItemsCount
                    action()
                }
            }
    }

}

@Composable
fun MoreMovieState(isError: Boolean, action: () -> Unit) {
    if (isError) {
        ErrorGetNextPage {
            action()
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(8.dp))
        }
    }
}
