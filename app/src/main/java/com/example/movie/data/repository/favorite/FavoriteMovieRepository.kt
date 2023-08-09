package com.example.movie.data.repository.favorite

import com.example.movie.data.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    suspend fun insertFavorite(movie: FavoriteMovieEntity)
    fun getAllFavorites(): Flow<List<FavoriteMovieEntity>>
    suspend fun deleteFavorite(movieId:Int)
    fun searchMovie(titleMovie:String): Flow<List<FavoriteMovieEntity>>
}