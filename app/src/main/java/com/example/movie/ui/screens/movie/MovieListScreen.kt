package com.example.movie.ui.screens.movie


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movie.PATH_IMAGE
import com.example.movie.ui.loading.LoadingScreen


@Composable
fun MovieListScreen(goToDetailsMovie: (Int) -> Unit) {
    val size = LocalConfiguration.current.screenHeightDp.dp - 80.dp
    val viewModel = hiltViewModel<MovieListViewModel>()
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        LoadingScreen()
    }

    if (state.showError) {

    }

    if (state.showData) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.height(size)) {
            items(state.listMovies) { item ->
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier.clickable { goToDetailsMovie(item.id) }
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    AsyncImage(
                        model = PATH_IMAGE + item.backdrop_path,
                        contentDescription = "imageMovie",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(6.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    MovieListScreen {

    }
}