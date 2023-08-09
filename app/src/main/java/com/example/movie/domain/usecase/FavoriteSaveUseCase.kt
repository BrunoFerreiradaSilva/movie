package com.example.movie.domain.usecase

import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import com.example.movie.presentation.ui.screens.favoritemovies.FavoriteMovieUiData
import com.example.movie.presentation.ui.screens.favoritemovies.FavoriteMovieUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteSaveUseCase @Inject constructor(private val repository: FavoriteMovieRepository) {

    suspend fun getAllFavorites(): Flow<List<FavoriteMovieUiData>> = flow {
        repository.getAllFavorites().collect { listFavorite ->
            val favoriteMovieUiState = listFavorite.map { favorite ->
                FavoriteMovieUiData(
                    title = favorite.title,
                    overview = favorite.overview,
                    backgroundPath = favorite.backdropPath,
                    releaseDate = favorite.releaseDate
                )
            }
            emit(favoriteMovieUiState)
        }
    }

    suspend fun searchInFavorite(title: String): Flow<List<FavoriteMovieUiData>> = flow {
        repository.searchMovie(title).collect {
            val searchMovie = it.map { favoriteMovieEntity ->
                FavoriteMovieUiData(
                    title = favoriteMovieEntity.title,
                    overview = favoriteMovieEntity.overview,
                    backgroundPath = favoriteMovieEntity.backdropPath,
                    releaseDate = favoriteMovieEntity.releaseDate
                )
            }
            emit(searchMovie)
        }
    }
}