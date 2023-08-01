package com.example.movie.ui.state

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
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movie.R
import com.valentinilk.shimmer.shimmer


@Composable
fun LoadingState() {
    Column() {
        TopAppBar(
            title = { Text(text = "Movie") },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "Go to favorite screen"
                    )
                }
            }
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }
            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }
            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }
            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }
            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }
            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }

            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }

            }
            item {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(200.dp)
                            .shimmer()
                            .background(Color.Gray)
                    )
                    Spacer(
                        modifier = Modifier
                            .shimmer()
                            .padding(6.dp)
                            .fillMaxWidth()
                            .size(16.dp)
                            .background(Color.Gray)

                    )

                }

            }
        }
    }
}