package com.example.movie.ui.screens.favorite

import androidx.lifecycle.ViewModel
import com.example.movie.ui.screens.detail.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor():ViewModel() {

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

}