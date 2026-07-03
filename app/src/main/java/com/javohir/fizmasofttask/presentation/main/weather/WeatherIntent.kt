package com.javohir.fizmasofttask.presentation.main.weather

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather
 * Description: User Action
 */
sealed class WeatherIntent {
    data class PermissionResult(val granted: Boolean) : WeatherIntent()
    data object Refresh : WeatherIntent()
}
