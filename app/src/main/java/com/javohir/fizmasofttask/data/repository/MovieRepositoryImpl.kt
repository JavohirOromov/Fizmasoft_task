package com.javohir.fizmasofttask.data.repository

import com.javohir.fizmasofttask.data.local.mapper.movieMapper.toDomain
import com.javohir.fizmasofttask.data.local.mapper.movieMapper.toEntity
import com.javohir.fizmasofttask.data.local.movies.MovieDao
import com.javohir.fizmasofttask.data.remote.api.movies.MovieApi
import com.javohir.fizmasofttask.data.remote.mapper.movieMapper.toDomain
import com.javohir.fizmasofttask.domain.model.Movie
import com.javohir.fizmasofttask.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macOS
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.repository
 * Description: Repository implementation
 */
class MovieRepositoryImpl @Inject constructor(
    private val dao: MovieDao,
    private val api: MovieApi
): MovieRepository {
    override suspend fun fetchRemoteMovies(): List<Movie> {
       return api.getMovies().results.map { it.toDomain() }
    }

    override suspend fun getCachedMovies(): List<Movie> =
        dao.getAll().map { it.toDomain() }

    override suspend fun cacheMovies(movies: List<Movie>) {
        dao.upsertAll(movies.map { it.toEntity() })
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return dao.getById(id)?.toDomain()
    }

}