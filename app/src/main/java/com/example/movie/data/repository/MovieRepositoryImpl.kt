package com.example.movie.data.repository

import com.example.movie.data.service.MovieService
import com.example.movie.domain.model.Genre
import com.example.movie.domain.model.MovieDetail
import com.example.movie.domain.model.MoviePopular
import com.example.quizdynamox.domain.helpers.DataState
import com.example.quizdynamox.domain.helpers.LoadingState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) : MovieRepository {
    override fun getMovie(): Flow<DataState<List<MoviePopular>>> = flow {
        emit(DataState.Loading(loadingState = LoadingState.Loading))
        delay(2000)
        try {
            val results = service.getPopularMovie().results
            emit(DataState.Data(data = results))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading(loadingState = LoadingState.Loading))
        delay(2000)
        try {
            val movieDetail = service.getMovieDetails(movieId)
            emit(DataState.Data(data = movieDetail))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }
}