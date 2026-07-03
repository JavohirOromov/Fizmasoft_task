package com.javohir.fizmasofttask.data.remote.dto.weatherDto

import com.google.gson.annotations.SerializedName

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.dto.weatherDto
 * Description:
 */

data class DailyDto(
    @SerializedName("temperature_2m_max") val tempMax: List<Double>,
    @SerializedName("temperature_2m_min") val tempMin: List<Double>,
)
