package com.javohir.fizmasofttask.domain.repository

import com.javohir.fizmasofttask.domain.model.GeoLocation

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.repository
 * Description: Repository interface
 */
interface LocationRepository {
    suspend fun getCurrentLocation(): GeoLocation?
}