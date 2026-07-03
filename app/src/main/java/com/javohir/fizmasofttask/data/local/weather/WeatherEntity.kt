package com.javohir.fizmasofttask.data.local.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javohir.fizmasofttask.domain.model.HourlyWeather

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local.weather
 * Description: Ob-havo cache (bitta qator, id = 0)
 */
@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val id: Int = 0,
    val temperature: Int,
    val condition: String,
    val conditionCode: Int,
    val high: Int,
    val low: Int,
    val humidity: Int,
    val windSpeed: Double,
    val feelsLike: Int,
    val hourly: List<HourlyWeather>,
)
