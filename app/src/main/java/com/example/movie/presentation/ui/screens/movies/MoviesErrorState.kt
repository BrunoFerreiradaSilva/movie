package com.example.movie.presentation.ui.screens.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie.R

@Composable
fun MoviesErrorState(retry: () -> Unit) {
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
                IconButton(onClick ={}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = stringResource(id = R.string.content_detail_screen),
                        tint = Color.White
                    )
                }
            }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.content_error),
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(Color.Red)
            )
            Text(
                text = stringResource(id = R.string.error_could_not_load_movie_list),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(60.dp))
            Button(onClick = { retry() }) {
                Text(text = stringResource(id = R.string.retry_button_text))
            }
        }
    }
}