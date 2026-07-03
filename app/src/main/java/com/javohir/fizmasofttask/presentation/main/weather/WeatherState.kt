package com.javohir.fizmasofttask.presentation.main.weather

import com.javohir.fizmasofttask.domain.model.Weather

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather
 * Description: UI state
 */
data class WeatherState(
    val hasLocationPermission: Boolean = false,
    val isLoading: Boolean = false,
    val weather: Weather? = null,
)
