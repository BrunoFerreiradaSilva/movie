package com.example.movie.ui.screens.favoritemovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.movie.PATH_IMAGE

@Composable
fun FavoriteMoviesScreen(goToMovieList: () -> Unit) {
    val viewModel = hiltViewModel<FavoriteMoviesViewModel>()
    val state by viewModel.uiState.collectAsState()
    val query = remember { mutableStateOf(TextFieldValue("")) }

    Column {
        AppBarTransform(
            goToMovieList = { goToMovieList() },
            openSearchField = { viewModel.openSearchField() },
            state = state.showSearch,
            textValue = query,
            searchMovie = { viewModel.searchMovie(query.value.text) },
            favoriteIsNotEmpty = state.favoriteMovie.isNotEmpty()
        )
        if (state.favoriteMovie.isEmpty()) {
            EmptyFavoriteMovies(state.messageError)
        } else {
            LazyColumn {
                items(state.favoriteMovie) { item ->
                    Card(
                        shape = ShapeDefaults.Small,
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                    ) {
                        Row(Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = PATH_IMAGE + item.backgroundPath,
                                contentDescription = "imageMovie"
                            )
                            Column {
                                Row {
                                    Text(
                                        text = item.title,
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .fillMaxWidth(0.6f),
                                        textAlign = TextAlign.Start,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Text(
                                        text = item.releaseDate,
                                        color = MaterialTheme.colors.primary,
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .fillMaxWidth(0.9f),
                                        textAlign = TextAlign.Start,
                                        fontSize = 14.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                                Text(
                                    text = item.overview,
                                    modifier = Modifier
                                        .padding(6.dp),
                                    textAlign = TextAlign.Start,
                                    fontSize = 14.sp,
                                    maxLines = 7,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppBarTransform(
    goToMovieList: () -> Unit,
    openSearchField: () -> Unit,
    state: Boolean,
    textValue: MutableState<TextFieldValue>,
    searchMovie: (String) -> Unit,
    favoriteIsNotEmpty: Boolean
) {
    if (!state) {
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
            title = { Text(text = "Favorites") },
            actions = {
                IconButton(onClick = { if (favoriteIsNotEmpty) openSearchField() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search favorite movie",
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = { },
            backgroundColor = MaterialTheme.colors.primary,
            actions = {
                IconButton(onClick = { openSearchField() }) {
                    TextField(value = textValue.value, onValueChange = {
                        textValue.value = it
                        searchMovie(textValue.value.text)
                    }, modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    if (textValue.value.text.isNotEmpty()) {
                                        textValue.value = textValue.value.copy("")
                                        searchMovie(textValue.value.text)
                                    } else {
                                        openSearchField()
                                    }
                                }
                            )
                        },
                        placeholder = { Text(text = "Search", color = Color.Black) }
                    )
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewFavorite() {
    FavoriteMoviesScreen {

    }
}