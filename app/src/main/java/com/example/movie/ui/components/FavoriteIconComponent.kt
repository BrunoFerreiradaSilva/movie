package com.example.movie.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIcon(isFavorite: Boolean, favoriteMovie: () -> Unit) {
    if (isFavorite){
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "favorite",
            modifier = Modifier
                .padding(6.dp)
                .clickable {
                    favoriteMovie()
                },
            tint = Color.Red
        )
    }else {
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "favorite",
            modifier = Modifier
                .padding(6.dp)
                .clickable {
                    favoriteMovie()
                },
            tint = Color.Red
        )
    }
}


@Preview
@Composable
fun IsFavorite() {
    FavoriteIcon(isFavorite = true) {

    }
}
@Preview
@Composable
fun NoFavorite() {
    FavoriteIcon(isFavorite = false) {

    }
}