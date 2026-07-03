package com.javohir.fizmasofttask.data.remote.dto.weatherDto

import com.google.gson.annotations.SerializedName

data class DailyDto(
    @SerializedName("temperature_2m_max") val tempMax: List<Double>,
    @SerializedName("temperature_2m_min") val tempMin: List<Double>,
)
