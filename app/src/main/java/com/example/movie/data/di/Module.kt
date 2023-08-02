package com.example.movie.data.di

import android.content.Context
import com.example.movie.BASE_URL
import com.example.movie.data.database.MovieDAO
import com.example.movie.data.database.MovieDataBase
import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import com.example.movie.data.repository.favorite.FavoriteMovieRepositoryImpl
import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.data.repository.movie.MovieRepositoryImpl
import com.example.movie.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesMovieRepository(
        movieService: MovieService
    ): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

    @Provides
    fun providesDAO(@ApplicationContext appContext: Context): MovieDAO {
        return MovieDataBase.getDatabase(appContext).movieDAO()
    }

    @Provides
    fun providesMovieFavorite(
        dao: MovieDAO
    ): FavoriteMovieRepository{
        return FavoriteMovieRepositoryImpl(dao)
    }

    @Provides
    fun providesRetrofit(): MovieService {
        val client = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                client
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }
}