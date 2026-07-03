package com.javohir.fizmasofttask.data.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.javohir.fizmasofttask.domain.model.GeoLocation
import com.javohir.fizmasofttask.domain.repository.LocationRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import androidx.core.content.edit
import kotlin.coroutines.resume

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.repository
 * Description: Repository implementation
 */
class LocationRepositoryImpl @Inject constructor(
    private val client: FusedLocationProviderClient,
    private val prefs: SharedPreferences,
): LocationRepository {
    override suspend fun getCurrentLocation(): GeoLocation? {
        val device = deviceLocation()
        if (device != null){
            cache(device)
            return device
        }
        return cached()
    }

    @SuppressLint("MissingPermission")
    private suspend fun deviceLocation(): GeoLocation? =
        suspendCancellableCoroutine { cont ->
            try {
                client.lastLocation
                    .addOnSuccessListener { loc ->
                        cont.resume(loc?.let { GeoLocation(it.latitude, it.longitude)
                        })
                    }
                    .addOnFailureListener { cont.resume(null) }
            } catch (e: SecurityException) {
                cont.resume(null)
            }
        }
    private fun cache(location: GeoLocation) {
        prefs.edit {
            putString(KEY_LAT, location.latitude.toString())
                .putString(KEY_LON, location.longitude.toString())
        }
    }

    private fun cached(): GeoLocation? {
        val lat = prefs.getString(KEY_LAT, null)?.toDoubleOrNull()
        val lon = prefs.getString(KEY_LON, null)?.toDoubleOrNull()
        return if (lat != null && lon != null) GeoLocation(lat, lon) else null
    }

    companion object {
        private const val KEY_LAT = "last_lat"
        private const val KEY_LON = "last_lon"
    }
}