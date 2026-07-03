package com.javohir.fizmasofttask.presentation.main.prayer

import com.javohir.fizmasofttask.domain.model.PrayerTimes

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer
 * Description: UI state
 */
data class PrayerState(
    val hasLocationPermission: Boolean = false,
    val isLoading: Boolean = false,
    val prayerTimes: PrayerTimes? = null,
    val nextPrayerName: String? = null,
    val countdown: String? = null,
)
