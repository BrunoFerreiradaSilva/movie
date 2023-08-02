package com.example.movie.data.response

import com.example.movie.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val genres: List<Genre>
)