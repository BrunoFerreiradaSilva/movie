package com.example.movie.data.service

import com.example.movie.domain.model.MoviePopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "ccc9f64c2e055de7ec081e9f903571a3"
const val DEFAULT_PAGE = 1

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): MoviePopularResponse
}