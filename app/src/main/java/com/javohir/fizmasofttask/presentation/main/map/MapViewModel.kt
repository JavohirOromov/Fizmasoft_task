package com.javohir.fizmasofttask.presentation.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.fizmasofttask.domain.usecase.GetCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.map
 * Description: ViewModel
 */
@HiltViewModel
class MapViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MapState())
    val state: StateFlow<MapState> = _state.asStateFlow()

    fun onAction(intent: MapIntent) {
        when (intent) {
            is MapIntent.PermissionResult -> {
                _state.update { it.copy(hasLocationPermission = intent.granted) }
                loadLocation()
            }
        }
    }

    private fun loadLocation() {
        viewModelScope.launch {
            _state.update { it.copy(location = getCurrentLocationUseCase()) }
        }
    }

}