package com.javohir.fizmasofttask.domain.model

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.model
 * Description: Ob-havo domain modeli
 */
data class Weather(
    val temperature: Int,
    val condition: String,
    val conditionCode: Int,
    val high: Int,
    val low: Int,
    val humidity: Int,
    val windSpeed: Double,
    val feelsLike: Int,
    val hourly: List<HourlyWeather> = emptyList(),
)

data class HourlyWeather(
    val time: String,
    val temperature: Int,
    val conditionCode: Int,
)
