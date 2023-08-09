package com.example.movie.domain.usecase

import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.domain.helpers.DataState
import com.example.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {

     operator fun  invoke(page:Int): Flow<DataState<List<Movie>>> {
        return repository.getMovie(page)
    }
}