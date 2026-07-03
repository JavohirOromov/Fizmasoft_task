package com.javohir.fizmasofttask.data.remote.mapper.movieMapper

import com.javohir.fizmasofttask.data.remote.dto.movieDto.MovieDto
import com.javohir.fizmasofttask.domain.model.Movie

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.mapper
 * Description: Mapper
 */

private const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title.orEmpty(),
    overview = overview.orEmpty(),
    posterUrl = posterPath?.let { IMAGE_BASE + it } as String,
    backdropUrl = backdropPath?.let { IMAGE_BASE + it } as String,
    rating = voteAverage ?: 0.0,
    year = releaseDate?.take(4).orEmpty(),
)