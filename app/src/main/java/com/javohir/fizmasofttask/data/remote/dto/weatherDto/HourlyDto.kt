package com.javohir.fizmasofttask.data.remote.dto.weatherDto

import com.google.gson.annotations.SerializedName

data class HourlyDto(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m") val temperature: List<Double>,
    @SerializedName("weather_code") val weatherCode: List<Int>,
)
