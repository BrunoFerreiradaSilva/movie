package com.example.movie.presentation.ui.screens.movies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.R
import com.valentinilk.shimmer.shimmer


@Composable
fun LoadingState(stateAnimation: Boolean) {
    Column {
        TopAppBar(
            backgroundColor = colorResource(id = R.color.purple_500),
            title = {
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.movie),
                    color = Color.White
                )
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = stringResource(id = R.string.content_detail_screen),
                        tint = Color.White
                    )
                }
            }
        )
        AnimatedVisibility(
            visible = stateAnimation,
            enter = fadeIn(animationSpec = keyframes {
                this.durationMillis = 300
            }),
            exit = fadeOut(
                animationSpec = keyframes {
                    this.durationMillis = 300
                }
            )
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                (0..5).toList().forEach { _ ->
                    item {
                        Card(
                            elevation = CardDefaults.cardElevation(2.dp),
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            colors = CardDefaults.cardColors(Color.White)
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .size(200.dp)
                                    .shimmer()
                                    .background(Color.LightGray.copy(alpha = 0.5f))
                            )
                            Spacer(
                                modifier = Modifier
                                    .shimmer()
                                    .padding(6.dp)
                                    .fillMaxWidth()
                                    .size(16.dp)
                                    .background(Color.LightGray.copy(alpha = 0.5f))
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun LoadingStateTrue() {
    LoadingState(stateAnimation = true)
}


@Preview
@Composable
fun LoadingStateFalse() {
    LoadingState(stateAnimation = false)
}