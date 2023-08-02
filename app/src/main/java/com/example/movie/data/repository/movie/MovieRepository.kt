package com.example.movie.data.repository.movie

import com.example.movie.data.response.MovieDetailsResponse
import com.example.movie.domain.model.Movie
import com.example.movie.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovie(): Flow<DataState<List<Movie>>>
    fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetailsResponse>>
}