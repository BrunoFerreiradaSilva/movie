package com.example.movie.domain.model

data class MoviePopular(
    val id: Int,
    val poster_path: String,
    val title: String,
    val backdrop_path: String,
    val overview: String,
    val release_date: String,
    val popularity: Float
)

data class MoviePopularResponse(
    val results: List<MoviePopular>
)
