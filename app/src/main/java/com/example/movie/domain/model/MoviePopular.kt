package com.example.movie.domain.model

import com.google.gson.annotations.SerializedName

data class MoviePopular(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
)

data class MoviePopularResponse(
    val results: List<MoviePopular>
)
