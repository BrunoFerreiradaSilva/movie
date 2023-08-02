package com.example.movie.ui.screens.favoritemovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.repository.favorite.FavoriteMovieRepository
import com.example.movie.ui.screens.movieDetails.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteMovieUiState(
    val favoriteMovie: List<FavoriteMovieUiData> = emptyList()
)

data class FavoriteMovieUiData(
    val title:String,
    val overview:String,
    val backgroundPath:String,
    val releaseDate:String
)

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(repository: FavoriteMovieRepository):ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteMovieUiState> =
        MutableStateFlow(
            FavoriteMovieUiState()
        )

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllFavorites().collect{listFavorite->
                listFavorite?.let {
                    val favoriteMovieUiState = it.map {favorite->
                        FavoriteMovieUiData(
                            title = favorite.title,
                            overview = favorite.overview,
                            backgroundPath = favorite.backdropPath,
                            releaseDate = favorite.releaseDate
                        )
                    }
                    _uiState.value = _uiState.value.copy(favoriteMovie = favoriteMovieUiState)
                }?: run {
                    _uiState.value = _uiState.value.copy(favoriteMovie = emptyList())
                }


            }
        }
    }

}