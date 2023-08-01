package com.example.movie.data.repository

import com.example.movie.domain.model.MoviePopular
import com.example.movie.domain.model.MoviePopularResponse
import com.example.quizdynamox.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovie(): Flow<DataState<List<MoviePopular>>>
}