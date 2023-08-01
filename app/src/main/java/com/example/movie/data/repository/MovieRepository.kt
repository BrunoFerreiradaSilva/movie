package com.example.movie.data.repository

import android.graphics.Movie
import com.example.movie.domain.model.Genre
import com.example.movie.domain.model.MovieDetail
import com.example.movie.domain.model.MoviePopular
import com.example.movie.domain.model.MoviePopularResponse
import com.example.quizdynamox.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovie(): Flow<DataState<List<MoviePopular>>>
    fun getMovieDetail(movieId:Int): Flow<DataState<MovieDetail>>
}