package com.example.movie.presentation.ui.screens.movieDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.data.response.MovieDetailsResponse
import com.example.movie.domain.helpers.DataState
import com.example.movie.domain.helpers.MOVIE_ID_INTENT
import com.example.movie.domain.model.Genre
import com.example.movie.domain.usecase.FavoriteUseCase
import com.example.movie.domain.usecase.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieDetailUiState(
    val showData: Boolean = false,
    val showError: Boolean = false,
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val movieDetail: MovieDetailUiData? = null
)

data class MovieDetailUiData(
    val id: Int,
    val title: String,
    val backgroundPath: String,
    val genre: List<Genre>,
    val releaseDate: String,
    val overView: String,
    val isFavorite: Boolean
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieDetailUseCase: MovieDetailUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_ID_INTENT])

    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(
            MovieDetailUiState()
        )

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            movieDetailUseCase.invoke(movieId).collect(::getMovieDetail)
        }
    }

    private fun getMovieDetail(state: DataState<MovieDetailsResponse>) {
        when (state) {
            is DataState.Data -> {
                viewModelScope.launch {
                    val movieUiData = MovieDetailUiData(
                        title = state.data.title,
                        genre = state.data.genres,
                        releaseDate = state.data.releaseDate,
                        overView = state.data.overview,
                        backgroundPath = state.data.backdropPath,
                        id = movieId,
                        isFavorite = _uiState.value.isFavorite
                    )

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        showError = false,
                        showData = true,
                        movieDetail = movieUiData
                    )

                    updateFavorite()

                }
            }

            is DataState.Error -> {
                _uiState.value =
                    _uiState.value.copy(isLoading = false, showError = true, showData = false)
            }

            is DataState.Loading -> {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = true,
                        showError = false,
                        showData = false,
                        isFavorite = _uiState.value.isFavorite
                    )
            }
        }
    }

    private fun updateFavorite() {
        viewModelScope.launch {
            favoriteUseCase.updateFavorite(_uiState.value.movieDetail?.id).collect { isFavorite ->
                _uiState.value = _uiState.value.copy(isFavorite = isFavorite)
            }
        }
    }

    fun favoriteMovie(movieDetail: MovieDetailUiData) {
        viewModelScope.launch {
            favoriteUseCase.favoriteMovie(movieDetail = movieDetail)
        }
    }

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch {
            favoriteUseCase.deleteFavorite(movieId)
        }
    }
}

