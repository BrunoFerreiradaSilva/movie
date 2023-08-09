package com.example.movie.domain.usecase

import com.example.movie.data.entity.FavoriteMovieEntity
import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import com.example.movie.presentation.ui.screens.favoritemovies.FavoriteMovieUiData
import com.example.movie.presentation.ui.screens.movieDetails.MovieDetailUiData
import com.example.movie.presentation.ui.screens.movies.MovieUiData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FavoriteUseCase @Inject constructor(private val repository: FavoriteMovieRepository) {

    suspend fun updateFavoriteMovie(): Flow<List<Int>> = flow {
        repository.getAllFavorites().collect { favoriteList ->
            val favoriteIds = favoriteList.map { it.id }
            emit(favoriteIds)
        }
    }

    suspend fun favoriteMovie(movie: FavoriteMovieEntity) {
        repository.insertFavorite(movie)
    }

    suspend fun deleteFavorite(movieId:Int){
        repository.deleteFavorite(movieId)
    }
}