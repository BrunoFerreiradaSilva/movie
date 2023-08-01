package com.example.movie.ui.screens


import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movie.mock.getMovies


@Composable
fun MovieListScreen(goToDetailsMovie: () -> Unit) {
    val size = LocalConfiguration.current.screenHeightDp.dp - 80.dp
    val listMovies = getMovies()
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.height(size)) {
        items(listMovies) { item ->
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                AsyncImage(
                    model = item.image,
                    contentDescription = "imageMovie",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = item.nameMovie,
                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
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