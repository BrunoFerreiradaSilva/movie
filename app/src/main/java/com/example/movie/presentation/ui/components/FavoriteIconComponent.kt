package com.example.movie.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.R

@Composable
fun FavoriteIcon(isFavorite: Boolean, favoriteMovie: () -> Unit) {
    if (isFavorite){
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = stringResource(id = R.string.favorites),
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
            contentDescription = stringResource(id = R.string.no_favorite_icon),
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
  FavoriteIcon(isFavorite = false) {

  }
}
@Preview
@Composable
fun NoFavorite() {
    FavoriteIcon(isFavorite = true) {

    }
}