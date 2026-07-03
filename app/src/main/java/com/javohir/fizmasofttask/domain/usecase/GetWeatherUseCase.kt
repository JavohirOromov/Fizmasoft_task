package com.javohir.fizmasofttask.domain.usecase

import com.javohir.fizmasofttask.core.util.Resource
import com.javohir.fizmasofttask.domain.model.Weather
import com.javohir.fizmasofttask.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.usecase
 * Description: Offline-first ob-havo (cache → remote → cache)
 */
class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading)

        val cached = repository.getCachedWeather()
        if (cached != null) emit(Resource.Success(cached))

        try {
            val remote = repository.fetchRemoteWeather(latitude, longitude)
            repository.cacheWeather(remote)
            emit(Resource.Success(remote))
        } catch (e: Exception) {
            if (cached == null) emit(Resource.Error(e.message ?: "Tarmoq xatosi"))
        }
    }
}
