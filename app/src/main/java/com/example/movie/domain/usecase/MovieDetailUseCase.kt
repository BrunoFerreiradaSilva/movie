package com.example.movie.domain.usecase

import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.data.response.MovieDetailsResponse
import com.example.movie.domain.helpers.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(movieId:Int): Flow<DataState<MovieDetailsResponse>> {
       return repository.getMovieDetail(movieId)
    }
}