package com.example.movie.ui.screens.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Preview(showSystemUi = true)
@Composable
fun MovieDetailsLoadingScreen(backgroundShimmer: Color = Color.LightGray.copy(alpha = 0.5f)) {
    Column {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back for movie list"
                    )
                }
            },
            title = {
                Spacer(modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .shimmer()
                    .padding(top = 4.dp)
                    .width(110.dp)
                    .size(24.dp)
                    .background(backgroundShimmer))
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "no favorite icon", tint = Color.Red)
                }
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .shimmer()
                .background(backgroundShimmer)
                .size(214.dp)
        )


        Spacer(modifier = Modifier
            .padding(horizontal = 8.dp)
            .shimmer()
            .padding(top = 4.dp)
            .width(110.dp)
            .size(24.dp)
            .background(backgroundShimmer))


        Spacer(modifier = Modifier
            .padding(horizontal = 8.dp)
            .shimmer()
            .padding(top = 8.dp)
            .width(170.dp)
            .size(24.dp)
            .background(backgroundShimmer))


        Spacer(modifier = Modifier
            .padding(horizontal = 8.dp)
            .shimmer()
            .padding(top = 8.dp)
            .width(200.dp)
            .size(24.dp)
            .background(backgroundShimmer))


        Spacer(modifier = Modifier
            .padding(horizontal = 8.dp)
            .shimmer()
            .padding(top = 10.dp)
            .fillMaxWidth()
            .size(120.dp)
            .background(backgroundShimmer))

    }
}