package com.javohir.fizmasofttask.presentation.main.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.fizmasofttask.core.util.Resource
import com.javohir.fizmasofttask.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.movies
 * Description: ViewModel
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovies: GetMoviesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<MoviesEvent>()
    val event = _event.asSharedFlow()

    init {
        onAction(MoviesIntent.Load)
    }

    fun onAction(intent: MoviesIntent) {
        when (intent) {
            is MoviesIntent.Load,
            is MoviesIntent.Refresh -> load()

            is MoviesIntent.MovieClicked ->
                _state.update { it.copy(selectedMovie = intent.movie) }

            is MoviesIntent.DismissDetail ->
                _state.update { it.copy(selectedMovie = null) }
        }
    }

    private fun load() {
        getMovies()
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading ->
                        _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update { it.copy(isLoading = false, movies = resource.data) }

                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false) }
                        _event.emit(MoviesEvent.ShowError(resource.message))
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}