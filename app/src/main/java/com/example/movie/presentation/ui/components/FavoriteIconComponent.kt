package com.example.movie.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoriteIcon(icon: ImageVector, favoriteMovie: () -> Unit) {
    val state = remember {
        mutableStateOf(true)
    }
    IconButton(onClick = {
        favoriteMovie()
        state.value = !state.value
    }) {
        AnimatedVisibility(visible = state.value, enter = scaleIn(), exit = scaleOut()) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.favorites),
                tint = Color.Red
            )
        }
        AnimatedVisibility(visible = !state.value, enter = scaleIn(), exit = scaleOut()) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.favorites),
                tint = Color.Red
            )
        }
    }
}

@Preview
@Composable
fun IsFavorite() {
    FavoriteIcon(icon = Icons.Default.Favorite) {

    }

}

@Preview
@Composable
fun NoFavorite() {
    FavoriteIcon(icon = Icons.Default.FavoriteBorder) {

    }

}