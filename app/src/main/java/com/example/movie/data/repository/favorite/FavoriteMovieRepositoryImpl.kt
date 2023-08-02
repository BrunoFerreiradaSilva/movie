package com.example.movie.data.repository.favorite

import com.example.movie.data.database.MovieDAO
import com.example.movie.data.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(private val dao: MovieDAO) :
    FavoriteMovieRepository {
    override suspend fun insertFavorite(movie: FavoriteMovieEntity) {
        dao.insertFavorite(movie)
    }

    override fun getAllFavorites(): Flow<List<FavoriteMovieEntity>> = flow {
        dao.getAllFavorites().collect { favoriteMoviesList ->
            favoriteMoviesList?.let { favoriteMovies ->
                emit(favoriteMovies)
            } ?: run {
                emit(emptyList())
            }
        }
    }

    override suspend fun deleteFavorite(movieId: Int) {
        dao.deleteFavorite(movieId)
    }

}