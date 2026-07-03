package com.javohir.fizmasofttask.presentation.main.movies

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.movies
 * Description: Event's
 */
sealed class MoviesEvent {
    data class ShowError(val message: String) : MoviesEvent()
}