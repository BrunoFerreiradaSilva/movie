package com.example.movie.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val id: Int,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val popularity: Float,
    val genres: List<Genre>
)