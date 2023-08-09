package com.example.movie.presentation.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.entity.FavoriteMovieEntity
import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.domain.helpers.DataState
import com.example.movie.domain.model.Movie
import com.example.movie.domain.usecase.FavoriteUseCase
import com.example.movie.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieUiState(
    val data: List<MovieUiData>,
    val showData: Boolean = false,
    val showError: Boolean = false,
    val isLoading: Boolean = true,
    val pageMovie: Int = 1,
    val errorNextPage: Boolean = false,
    val loadingNextPage: Boolean = false,
    val isFavorite: Boolean = false
)

data class MovieUiData(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val backdropPath: String,
    val isFavorite: Boolean,
)

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<MovieUiState> =
        MutableStateFlow(MovieUiState(data = emptyList()))

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            movieUseCase.invoke(_uiState.value.pageMovie).collect(::getMovie)
        }
    }

    private fun getMovie(state: DataState<List<Movie>>) {
        when (state) {
            is DataState.Data -> {


                val optionUi = state.data.map {
                    MovieUiData(
                        id = it.id,
                        title = it.title,
                        releaseDate = it.releaseDate,
                        overview = it.overview,
                        backdropPath = it.posterPath,
                        isFavorite = _uiState.value.isFavorite
                    )
                }

                _uiState.value = _uiState.value.copy(
                    showData = true,
                    isLoading = false,
                    showError = false,
                    data = optionUi
                )
                updateFavorite()
            }

            is DataState.Error -> {
                _uiState.value =
                    _uiState.value.copy(
                        showError = true,
                        isLoading = false,
                        showData = false
                    )
            }

            is DataState.Loading -> {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = true,
                        showData = false,
                        showError = false,
                    )
            }
        }

    }

    fun retry() {
        viewModelScope.launch {
            movieUseCase.invoke(_uiState.value.pageMovie).collect(::getMovie)
        }
    }

    fun getMoreMovies() {
        viewModelScope.launch {
            val updateList = mutableListOf<MovieUiData>()
            val currentList = _uiState.value.data
            val nexPage = _uiState.value.pageMovie + 1

            movieUseCase.invoke(nexPage).collect { state ->
                when (state) {
                    is DataState.Data -> {

                        val optionUi = state.data.map {
                            MovieUiData(
                                id = it.id,
                                title = it.title,
                                releaseDate = it.releaseDate,
                                overview = it.overview,
                                backdropPath = it.posterPath,
                                isFavorite = _uiState.value.isFavorite
                            )
                        }

                        updateList.addAll(currentList)
                        updateList.addAll(optionUi)

                        _uiState.value = _uiState.value.copy(
                            data = updateList,
                            errorNextPage = false,
                            loadingNextPage = false,
                            pageMovie = nexPage
                        )
                    }

                    is DataState.Error -> {
                        _uiState.value =
                            _uiState.value.copy(errorNextPage = true, loadingNextPage = false)
                    }

                    is DataState.Loading -> {
                        _uiState.value =
                            _uiState.value.copy(errorNextPage = false, loadingNextPage = true)
                    }
                }
            }
        }
    }

    private fun updateFavorite() {
        viewModelScope.launch {
            favoriteUseCase.updateFavorite(_uiState.value.data).collect { movieFavorite ->
                _uiState.value = _uiState.value.copy(data = movieFavorite)
            }
        }
    }

    fun favoriteMovie(movieData: MovieUiData) {
        viewModelScope.launch {
            favoriteUseCase.favoriteMovie(movieData = movieData)
        }
    }

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch {
            favoriteUseCase.deleteFavorite(movieId)
        }
    }

}