package com.javohir.fizmasofttask.data.local.mapper.movieMapper

import com.javohir.fizmasofttask.data.local.movies.MovieEntity
import com.javohir.fizmasofttask.domain.model.Movie

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local.mapper.movieMapper
 * Description: MovieEntity <-> Movie (domain) mapper
 */

fun MovieEntity.toDomain(): Movie = Movie(
    id, title, overview, posterUrl, backdropUrl, rating, year,
)

fun Movie.toEntity(): MovieEntity = MovieEntity(
    id, title, overview, posterUrl, backdropUrl, rating, year,
)