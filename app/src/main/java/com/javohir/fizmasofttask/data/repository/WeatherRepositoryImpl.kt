package com.javohir.fizmasofttask.data.repository

import com.javohir.fizmasofttask.data.local.mapper.weatherMapper.toDomain
import com.javohir.fizmasofttask.data.local.mapper.weatherMapper.toEntity
import com.javohir.fizmasofttask.data.local.weather.WeatherDao
import com.javohir.fizmasofttask.data.remote.api.weather.WeatherApi
import com.javohir.fizmasofttask.data.remote.mapper.weatherMapper.toDomain
import com.javohir.fizmasofttask.domain.model.Weather
import com.javohir.fizmasofttask.domain.repository.WeatherRepository
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.repository
 * Description: Sof data — Open-Meteo + Room cache
 */
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: WeatherDao,
) : WeatherRepository {

    override suspend fun fetchRemoteWeather(latitude: Double, longitude: Double): Weather =
        api.getWeather(latitude, longitude).toDomain()

    override suspend fun getCachedWeather(): Weather? =
        dao.get()?.toDomain()

    override suspend fun cacheWeather(weather: Weather) =
        dao.upsert(weather.toEntity())
}
