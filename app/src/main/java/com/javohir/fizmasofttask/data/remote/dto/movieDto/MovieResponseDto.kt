package com.javohir.fizmasofttask.data.remote.dto.movieDto

import com.google.gson.annotations.SerializedName

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.dto
 * Description: 
 */
data class MovieResponseDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
)
