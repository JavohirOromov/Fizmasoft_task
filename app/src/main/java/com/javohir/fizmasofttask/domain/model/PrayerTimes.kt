package com.javohir.fizmasofttask.domain.model

/**
 * Created by: Javohir Oromov macOS
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.model
 * Description: PrayerTimes data
 */
data class PrayerTimes(
    val date: String,
    val times: List<PrayerTime>
)

data class PrayerTime(
    val name: String,
    val time: String
)
