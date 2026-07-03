package com.javohir.fizmasofttask.presentation.main.weather

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather
 * Description: Event's
 */
sealed class WeatherEvent {
    data class ShowError(val message: String) : WeatherEvent()
}
