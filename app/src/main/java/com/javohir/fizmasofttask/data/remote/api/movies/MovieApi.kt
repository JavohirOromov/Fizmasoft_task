package com.javohir.fizmasofttask.data.remote.api.movies

import com.javohir.fizmasofttask.data.remote.dto.movieDto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.api.movies
 * Description: Api Service
 */
interface MovieApi {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): MovieResponseDto

}