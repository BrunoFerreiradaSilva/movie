package com.example.movie.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FavoriteMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val isFavorite:Boolean
)