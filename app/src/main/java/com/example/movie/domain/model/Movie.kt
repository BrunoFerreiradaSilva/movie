package com.example.movie.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String
)
