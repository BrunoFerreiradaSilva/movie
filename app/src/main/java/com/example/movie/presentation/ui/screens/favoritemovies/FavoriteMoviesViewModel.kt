package com.example.movie.presentation.ui.screens.favoritemovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteMovieUiState(
    val favoriteMovie: List<FavoriteMovieUiData> = emptyList(),
    val isOpen: Boolean = false,
    val showSearch: Boolean = false,
    val messageError: String = ""
)

data class FavoriteMovieUiData(
    val title: String,
    val overview: String,
    val backgroundPath: String,
    val releaseDate: String
)

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(private val repository: FavoriteMovieRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteMovieUiState> =
        MutableStateFlow(
            FavoriteMovieUiState()
        )

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllFavorites().collect { listFavorite ->
                val favoriteMovieUiState = listFavorite.map { favorite ->
                    FavoriteMovieUiData(
                        title = favorite.title,
                        overview = favorite.overview,
                        backgroundPath = favorite.backdropPath,
                        releaseDate = favorite.releaseDate
                    )
                }
                if (favoriteMovieUiState.isEmpty()){
                    _uiState.value = _uiState.value.copy(messageError = "Você ainda não tem nenhum favorito !")
                }
                _uiState.value = _uiState.value.copy(favoriteMovie = favoriteMovieUiState)
            }
        }
    }

    fun openSearchField() {
        val isOpen = _uiState.value.isOpen
        if (isOpen) {
            _uiState.value = _uiState.value.copy(isOpen = false, showSearch = false)
        } else {
            _uiState.value = _uiState.value.copy(isOpen = true, showSearch = true)
        }
    }

    fun searchMovie(title: String) {
        viewModelScope.launch {
            repository.searchMovie(title).collect {
                val searchMovie = it.map { favoriteMovieEntity ->
                    FavoriteMovieUiData(
                        title = favoriteMovieEntity.title,
                        overview = favoriteMovieEntity.overview,
                        backgroundPath = favoriteMovieEntity.backdropPath,
                        releaseDate = favoriteMovieEntity.releaseDate
                    )
                }
                if (searchMovie.isEmpty()){
                    _uiState.value = _uiState.value.copy(messageError = "Não foi possivel encontrar o filme.")
                }
                _uiState.value = _uiState.value.copy(favoriteMovie = searchMovie)
            }
        }
    }

}