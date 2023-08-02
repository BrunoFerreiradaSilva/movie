package com.example.movie.data.repository.movie

import com.example.movie.data.response.MovieDetailsResponse
import com.example.movie.data.service.MovieService
import com.example.movie.domain.helpers.DataState
import com.example.movie.domain.helpers.LoadingState
import com.example.movie.domain.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) : MovieRepository {
    override fun getMovie(): Flow<DataState<List<Movie>>> = flow {
        emit(DataState.Loading(loadingState = LoadingState.Loading))
        delay(2000)
        try {
            val results = service.getPopularMovie().results
            val movie = results.map { movie ->
                Movie(
                    id = movie.id,
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.posterPath,
                    releaseDate = changeDataForBr(movie.releaseDate)
                )
            }

            emit(DataState.Data(data = movie))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<DataState<MovieDetailsResponse>> = flow {
        emit(DataState.Loading(loadingState = LoadingState.Loading))
        delay(2000)
        try {
            val movieDetailDto = service.getMovieDetails(movieId)
            val movieDetail = MovieDetailsResponse(
                id = movieDetailDto.id,
                title = movieDetailDto.title,
                overview = movieDetailDto.overview,
                backdropPath = movieDetailDto.backdropPath,
                releaseDate = changeDataForBr(movieDetailDto.releaseDate),
                genres = movieDetailDto.genres
            )
            emit(DataState.Data(data = movieDetail))
        } catch (error: Exception) {
            emit(DataState.Error(error = error))
        }
    }

    private fun changeDataForBr(currentValue: String): String {
        val formatOrigin = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val data = formatOrigin.parse(currentValue)

        val formatBr = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatBr.format(data)
    }
}