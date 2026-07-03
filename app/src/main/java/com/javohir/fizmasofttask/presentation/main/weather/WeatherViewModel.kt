package com.javohir.fizmasofttask.presentation.main.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.fizmasofttask.core.util.Resource
import com.javohir.fizmasofttask.domain.usecase.GetCurrentLocationUseCase
import com.javohir.fizmasofttask.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather
 * Description: ViewModel
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentLocation: GetCurrentLocationUseCase,
    private val getWeather: GetWeatherUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<WeatherEvent>()
    val event = _event.asSharedFlow()

    fun onAction(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.PermissionResult -> {
                _state.update { it.copy(hasLocationPermission = intent.granted) }
                load()
            }
            is WeatherIntent.Refresh -> load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            val loc = getCurrentLocation()
            getWeather(loc.latitude, loc.longitude).collect { resource ->
                when (resource) {
                    is Resource.Loading ->
                        _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update { it.copy(isLoading = false, weather = resource.data) }

                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false) }
                        _event.emit(WeatherEvent.ShowError(resource.message))
                    }
                }
            }
        }
    }
}
