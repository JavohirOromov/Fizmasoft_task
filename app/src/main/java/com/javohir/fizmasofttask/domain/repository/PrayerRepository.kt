package com.javohir.fizmasofttask.domain.repository

import com.javohir.fizmasofttask.domain.model.PrayerTimes

/**
 * Created by: Javohir Oromov macOS
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.repository
 * Description: Repository interface
 */
interface PrayerRepository {
    suspend fun fetchRemote(latitude: Double, longitude: Double, date: String): PrayerTimes
    suspend fun getCached(): PrayerTimes?
    suspend fun cache(prayerTimes: PrayerTimes)
}