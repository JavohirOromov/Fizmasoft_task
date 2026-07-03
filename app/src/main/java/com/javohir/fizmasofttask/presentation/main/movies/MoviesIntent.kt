package com.javohir.fizmasofttask.presentation.main.movies

import com.javohir.fizmasofttask.domain.model.Movie

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.movies
 * Description: User Action
 */
sealed class MoviesIntent {
    data object Load : MoviesIntent()
    data object Refresh : MoviesIntent()
    data class MovieClicked(val movie: Movie) : MoviesIntent()
    data object DismissDetail : MoviesIntent()
}