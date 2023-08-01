package com.example.movie.data.di

import com.example.movie.BASE_URL
import com.example.movie.data.repository.MovieRepository
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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