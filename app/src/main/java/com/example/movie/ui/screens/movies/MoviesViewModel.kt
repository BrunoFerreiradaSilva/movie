package com.example.movie.ui.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.entity.FavoriteMovieEntity
import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import com.example.movie.data.repository.movie.MovieRepository
import com.example.movie.domain.helpers.DataState
import com.example.movie.domain.model.Movie
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
    val loadingNextPage: Boolean = false
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
    private val remoteRepository: MovieRepository,
    private val localRepository: FavoriteMovieRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<MovieUiState> =
        MutableStateFlow(MovieUiState(data = emptyList()))

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            remoteRepository.getMovie(_uiState.value.pageMovie).collect(::getMovie)
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
                        isFavorite = false
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
            remoteRepository.getMovie(_uiState.value.pageMovie).collect(::getMovie)
        }
    }

    fun getMoreMovies() {
        viewModelScope.launch {
            val updateList = mutableListOf<MovieUiData>()
            val currentList = _uiState.value.data
            val nexPage = _uiState.value.pageMovie + 1
            val n =1

            remoteRepository.getMovie(nexPage).collect { state ->
                when (state) {
                    is DataState.Data -> {

                        val optionUi = state.data.map {
                            MovieUiData(
                                id = it.id,
                                title = it.title,
                                releaseDate = it.releaseDate,
                                overview = it.overview,
                                backdropPath = it.posterPath,
                                isFavorite = false
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
            localRepository.getAllFavorites().collect { favoriteList ->
                val favoriteIds: List<Int> = favoriteList.map { it.id } ?: emptyList()
                val updateList = _uiState.value.data.map {
                    MovieUiData(
                        id = it.id,
                        title = it.title,
                        backdropPath = it.backdropPath,
                        overview = it.overview,
                        releaseDate = it.releaseDate,
                        isFavorite = favoriteIds.contains(it.id)
                    )
                }
                _uiState.value = _uiState.value.copy(data = updateList)
            }
        }
    }

    fun favoriteMovie(movie: MovieUiData) {
        viewModelScope.launch {
            val favorite = FavoriteMovieEntity(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                releaseDate = movie.releaseDate,
                backdropPath = movie.backdropPath,
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