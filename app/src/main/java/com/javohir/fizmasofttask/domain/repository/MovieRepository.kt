package com.javohir.fizmasofttask.domain.repository

import com.javohir.fizmasofttask.domain.model.Movie

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.repository
 * Description: Repository interface
 */
interface MovieRepository {
    suspend fun fetchRemoteMovies(): List<Movie>
    suspend fun getCachedMovies(): List<Movie>
    suspend fun cacheMovies(movies: List<Movie>)
    suspend fun getMovieById(id: Int): Movie?
}