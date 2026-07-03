package com.javohir.fizmasofttask.data.remote.mapper.weatherMapper

import com.javohir.fizmasofttask.data.remote.dto.weatherDto.WeatherResponseDto
import com.javohir.fizmasofttask.domain.model.HourlyWeather
import com.javohir.fizmasofttask.domain.model.Weather
import java.time.LocalTime
import kotlin.math.roundToInt

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.mapper.weatherMapper
 * Description: Open-Meteo DTO -> domain
 */
fun WeatherResponseDto.toDomain(): Weather = Weather(
    temperature = current.temperature.roundToInt(),
    condition = weatherCodeToText(current.weatherCode),
    conditionCode = current.weatherCode,
    high = daily.tempMax.firstOrNull()?.roundToInt() ?: 0,
    low = daily.tempMin.firstOrNull()?.roundToInt() ?: 0,
    humidity = current.humidity,
    windSpeed = current.windSpeed,
    feelsLike = current.apparentTemperature.roundToInt(),
    hourly = buildHourly(),
)

// hourly[i] = shu kunning i-soati (00:00 dan). Joriy soatdan boshlab 6 tasi.
private fun WeatherResponseDto.buildHourly(): List<HourlyWeather> {
    val start = LocalTime.now().hour
    val end = minOf(start + 6, hourly.time.size)
    if (start >= end) return emptyList()
    return (start until end).map { i ->
        HourlyWeather(
            time = hourly.time[i].substringAfter("T"),   // "14:00"
            temperature = hourly.temperature[i].roundToInt(),
            conditionCode = hourly.weatherCode[i],
        )
    }
}

fun weatherCodeToText(code: Int): String = when (code) {
    0 -> "Ochiq"
    1 -> "Asosan ochiq"
    2 -> "Qisman bulutli"
    3 -> "Bulutli"
    45, 48 -> "Tumanli"
    51, 53, 55 -> "Mayda yomg'ir"
    56, 57, 61, 63, 65, 66, 67 -> "Yomg'ir"
    71, 73, 75, 77 -> "Qor"
    80, 81, 82 -> "Jala"
    85, 86 -> "Qor jalasi"
    95, 96, 99 -> "Momaqaldiroq"
    else -> "—"
}
