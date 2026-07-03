package com.javohir.fizmasofttask.data.remote.api.prayer

import com.javohir.fizmasofttask.data.remote.dto.prayer.PrayerResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.api.prayer
 * Description: Api Service
 */
interface PrayerApi {

    @GET("v1/timings/{date}")
    suspend fun getTimings(
        @Path("date") date: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("method") method: Int = 3,
    ): PrayerResponseDto

}