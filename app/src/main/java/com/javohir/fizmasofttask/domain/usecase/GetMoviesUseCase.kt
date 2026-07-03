package com.javohir.fizmasofttask.domain.usecase

import com.javohir.fizmasofttask.core.util.Resource
import com.javohir.fizmasofttask.domain.model.Movie
import com.javohir.fizmasofttask.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.usecase
 * Description: Use Case
 */
class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>>{
        return flow {
            emit(Resource.Loading)

            val cached = repository.getCachedMovies()
            if (cached.isNotEmpty()) emit(Resource.Success(cached))

            try {
                val remote = repository.fetchRemoteMovies()
                repository.cacheMovies(remote)
                emit(Resource.Success(remote))
            } catch (e: Exception) {
                if (cached.isEmpty()) emit(Resource.Error(e.message ?: "Tarmoq xatosi"))
            }
        }
    }

}