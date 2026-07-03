package com.javohir.fizmasofttask.data.remote.dto.weatherDto

import com.google.gson.annotations.SerializedName

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.dto.weatherDto
 * Description:
 */
data class WeatherResponseDto(
    @SerializedName("current") val current: CurrentDto,
    @SerializedName("daily") val daily: DailyDto,
    @SerializedName("hourly") val hourly: HourlyDto,
)
