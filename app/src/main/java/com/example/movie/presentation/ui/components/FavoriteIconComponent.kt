package com.example.movie.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.ui.unit.dp
import com.example.movie.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoriteIcon(isFavorite: Boolean, favoriteMovie: () -> Unit) {
    AnimatedVisibility(visible = isFavorite, enter = scaleIn(), exit = scaleOut()) {
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
    }
    AnimatedVisibility(visible = !isFavorite, enter = scaleIn(), exit = scaleOut()) {
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