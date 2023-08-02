package com.example.movie.data.response

import com.example.movie.domain.model.Movie

data class PopularMovieResponse(
    val results: List<Movie>
)