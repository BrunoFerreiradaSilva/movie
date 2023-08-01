package com.example.movie.ui.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Preview(showSystemUi = true)
@Composable
fun LoadingScreen() {
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
    }
}