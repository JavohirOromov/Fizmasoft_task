package com.javohir.fizmasofttask.presentation.main.prayer

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer
 * Description: Event's
 */
sealed class PrayerEvent {
    data class ShowError(val message: String) : PrayerEvent()
}
