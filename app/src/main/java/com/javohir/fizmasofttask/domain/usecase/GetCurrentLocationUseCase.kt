package com.javohir.fizmasofttask.domain.usecase

import com.javohir.fizmasofttask.domain.model.GeoLocation
import com.javohir.fizmasofttask.domain.repository.LocationRepository
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.usecase
 * Description: Use Case
 */
class GetCurrentLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(): GeoLocation{
        return repository.getCurrentLocation() ?: DEFAULT_LOCATION
    }

    companion object {
        val DEFAULT_LOCATION = GeoLocation(latitude = 41.311081, longitude = 69.240562)
    }
}