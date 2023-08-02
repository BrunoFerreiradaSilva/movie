package com.example.movie.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.data.entity.FavoriteMovieEntity

private const val DB_NAME = "movie_database"

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 1,
    exportSchema = true
)

abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getDatabase(
            context: Context,
        ): MovieDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}