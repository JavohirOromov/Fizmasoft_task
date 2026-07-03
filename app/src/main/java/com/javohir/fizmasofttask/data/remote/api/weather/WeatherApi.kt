package com.javohir.fizmasofttask.data.remote.api.weather

import com.javohir.fizmasofttask.data.remote.dto.weatherDto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.api.weather
 * Description: Open-Meteo
 */
interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String =
            "temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,wind_speed_10m",
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("hourly") hourly: String = "temperature_2m,weather_code",
        @Query("forecast_days") forecastDays: Int = 1,
        @Query("timezone") timezone: String = "auto",
    ): WeatherResponseDto
}
