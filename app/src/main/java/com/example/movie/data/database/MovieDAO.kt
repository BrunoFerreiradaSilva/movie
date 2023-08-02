package com.example.movie.data.database


import androidx.room.*
import com.example.movie.data.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface MovieDAO {

    @Query("SELECT * FROM `favoriteentity` ORDER BY id")
    fun getAllFavorites(): Flow<List<FavoriteMovieEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(playerEntity: FavoriteMovieEntity)

    @Query("DELETE FROM `favoriteentity` WHERE id =:movieId")
    suspend fun deleteFavorite(movieId: Int)

}
