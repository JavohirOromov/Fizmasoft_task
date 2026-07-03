package com.javohir.fizmasofttask.data.local.mapper.weatherMapper

import com.javohir.fizmasofttask.data.local.weather.WeatherEntity
import com.javohir.fizmasofttask.domain.model.Weather

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local.mapper.weatherMapper
 * Description: WeatherEntity <-> Weather (domain). Hourly cache qilinmaydi.
 */
fun WeatherEntity.toDomain(): Weather = Weather(
    temperature = temperature,
    condition = condition,
    conditionCode = conditionCode,
    high = high,
    low = low,
    humidity = humidity,
    windSpeed = windSpeed,
    feelsLike = feelsLike,
    hourly = hourly,
)

fun Weather.toEntity(): WeatherEntity = WeatherEntity(
    id = 0,
    temperature = temperature,
    condition = condition,
    conditionCode = conditionCode,
    high = high,
    low = low,
    humidity = humidity,
    windSpeed = windSpeed,
    feelsLike = feelsLike,
    hourly = hourly,
)
