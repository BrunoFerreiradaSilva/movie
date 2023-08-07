package com.example.movie.ui.screens.movieDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.MOVIE_ID_INTENT
import com.example.movie.data.entity.FavoriteMovieEntity
import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.data.response.MovieDetailsResponse
import com.example.movie.domain.helpers.DataState
import com.example.movie.domain.model.Genre
import com.example.movie.ui.screens.movies.MovieUiData
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
    private val remoteRepository: MovieRepository,
    private val localRepository: FavoriteMovieRepository
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[MOVIE_ID_INTENT])

    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(
            MovieDetailUiState()
        )

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            remoteRepository.getMovieDetail(movieId).collect(::getMovieDetail)
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
            localRepository.getAllFavorites().collect { favoriteList ->
                val favoriteIds: List<Int> = favoriteList?.map { it.id } ?: emptyList()
                _uiState.value =
                    _uiState.value.copy(isFavorite = favoriteIds.contains(_uiState.value.movieDetail?.id))
            }
        }
    }


    fun favoriteMovie(movie: MovieDetailUiData) {
        viewModelScope.launch {
            val favorite = FavoriteMovieEntity(
                id = movie.id,
                title = movie.title,
                overview = movie.overView,
                releaseDate = movie.releaseDate,
                backdropPath = movie.backgroundPath,
                isFavorite = true
            )
            localRepository.insertFavorite(favorite)
        }
    }

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch {
            localRepository.deleteFavorite(movieId)
        }
    }

}
