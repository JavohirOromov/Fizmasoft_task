package com.javohir.fizmasofttask.presentation.main.prayer

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer
 * Description: User Action
 */
sealed class PrayerIntent {
    data class PermissionResult(val granted: Boolean) : PrayerIntent()
    data object Refresh : PrayerIntent()
}
