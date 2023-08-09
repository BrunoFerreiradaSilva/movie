package com.example.movie.domain.usecase

import com.example.movie.data.entity.FavoriteMovieEntity
import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import com.example.movie.presentation.ui.screens.movieDetails.MovieDetailUiData
import com.example.movie.presentation.ui.screens.movies.MovieUiData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FavoriteUseCase @Inject constructor(private val repository: FavoriteMovieRepository) {

    suspend fun updateFavorite(movieList:List<MovieUiData>): Flow<List<MovieUiData>> = flow {
        repository.getAllFavorites().collect { favoriteList ->
            val favoriteIds = favoriteList.map { it.id }
            val movie = movieList.map {
                MovieUiData(
                    id = it.id,
                    title = it.title,
                    backdropPath = it.backdropPath,
                    overview = it.overview,
                    releaseDate = it.releaseDate,
                    isFavorite = favoriteIds.contains(it.id)
                )
            }
            emit(movie)
        }
    }

    suspend fun updateFavorite(movieId: Int?): Flow<Boolean> = flow{
        repository.getAllFavorites().collect {favoriteList ->
            val favoriteIds = favoriteList.map { it.id }
            emit(favoriteIds.contains(movieId))
        }
    }

    suspend fun favoriteMovie(movieDetail: MovieDetailUiData) {
        val movie = FavoriteMovieEntity(
            id = movieDetail.id,
            title = movieDetail.title,
            releaseDate = movieDetail.releaseDate,
            overview = movieDetail.overView,
            backdropPath = movieDetail.backgroundPath,
            isFavorite = movieDetail.isFavorite
        )
        repository.insertFavorite(movie)
    }

    suspend fun favoriteMovie(movieData: MovieUiData) {
        val movie = FavoriteMovieEntity(
            id = movieData.id,
            title = movieData.title,
            releaseDate = movieData.releaseDate,
            overview = movieData.overview,
            backdropPath = movieData.backdropPath,
            isFavorite = movieData.isFavorite
        )
        repository.insertFavorite(movie)
    }

    suspend fun deleteFavorite(movieId:Int){
        repository.deleteFavorite(movieId)
    }
}