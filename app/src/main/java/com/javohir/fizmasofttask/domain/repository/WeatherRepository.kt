package com.javohir.fizmasofttask.domain.repository

import com.javohir.fizmasofttask.domain.model.Weather

interface WeatherRepository {
    suspend fun fetchRemoteWeather(latitude: Double, longitude: Double): Weather
    suspend fun getCachedWeather(): Weather?
    suspend fun cacheWeather(weather: Weather)
}
