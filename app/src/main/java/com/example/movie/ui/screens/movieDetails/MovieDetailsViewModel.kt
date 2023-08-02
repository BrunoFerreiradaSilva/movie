package com.example.movie.ui.screens.movieDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.MOVIE_ID_INTENT
import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.data.response.MovieDetailsResponse
import com.example.movie.domain.helpers.DataState
import com.example.movie.domain.model.Genre
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
    val title: String,
    val backgroundPath: String,
    val genre: List<Genre>,
    val releaseDate: String,
    val overView: String
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_ID_INTENT])

    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(
            MovieDetailUiState(
                title = "",
                genre = emptyList(),
                releaseDate = "",
                overView = "",
                backgroundPath = ""
            )
        )

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getMovieDetail(movieId).collect(::getMovieDetail)
        }
    }

    private fun getMovieDetail(state: DataState<MovieDetailsResponse>) {
        when (state) {
            is DataState.Data -> {
                viewModelScope.launch {


                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        showError = false,
                        showData = true,
                        title = state.data.title,
                        genre = state.data.genres,
                        releaseDate = state.data.releaseDate,
                        overView = state.data.overview,
                        backgroundPath = state.data.backdropPath
                    )

                }
            }

            is DataState.Error -> {
                _uiState.value =
                    _uiState.value.copy(isLoading = false, showError = true, showData = false)
            }

            is DataState.Loading -> {
                _uiState.value =
                    _uiState.value.copy(isLoading = true, showError = false, showData = false)
            }
        }
    }


}

