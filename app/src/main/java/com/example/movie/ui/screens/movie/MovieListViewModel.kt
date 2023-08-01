package com.example.movie.ui.screens.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.repository.MovieRepository
import com.example.movie.domain.model.MoviePopular
import com.example.quizdynamox.domain.helpers.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieUiState(
   val listMovies: List<MoviePopular> = emptyList(),
   val showData: Boolean = false,
   val showError: Boolean = false,
   val isLoading:Boolean = true,
   val isFavorite:Boolean = false
)

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    private val _uiState: MutableStateFlow<MovieUiState> =
        MutableStateFlow(MovieUiState())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getMovie().collect(::getMovie)
        }
    }

    private fun getMovie(state:DataState<List<MoviePopular>>){
        when(state){
            is DataState.Data -> {
                _uiState.value = _uiState.value.copy(showData = true, isLoading = false, showError = false, listMovies = state.data)
            }
            is DataState.Error -> {
                _uiState.value = _uiState.value.copy(showError = true, isLoading = false, showData = false)
            }
            is DataState.Loading -> {
                _uiState.value = _uiState.value.copy(isLoading = true, showData = false, showError = false)
            }
        }

    }

    fun favoriteMovie(){

    }

    fun removeFavorite(){

    }

}