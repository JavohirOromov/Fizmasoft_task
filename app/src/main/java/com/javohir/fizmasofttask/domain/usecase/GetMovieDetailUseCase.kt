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
 * Description: UseCase
 */
class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Movie>>{
        return flow {
            emit(Resource.Loading)

            val movie = repository.getMovieById(id)
            if (movie != null) emit(Resource.Success(movie))
            else emit(Resource.Error("Kino Topilmadi"))
        }
    }
}