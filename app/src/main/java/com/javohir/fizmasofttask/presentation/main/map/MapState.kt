package com.javohir.fizmasofttask.presentation.main.map

import com.javohir.fizmasofttask.domain.model.GeoLocation

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.map
 * Description: UI state
 */

data class MapState(
    val hasLocationPermission: Boolean = false,
    val location: GeoLocation? = null,
)