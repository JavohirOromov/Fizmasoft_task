package com.javohir.fizmasofttask.presentation.main.movies

import com.javohir.fizmasofttask.domain.model.Movie

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.movies
 * Description: UI state
 */
data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val selectedMovie: Movie? = null,
)