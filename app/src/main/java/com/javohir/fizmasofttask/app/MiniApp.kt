package com.javohir.fizmasofttask.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by: Javohir Oromov macOS
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask
 * Description: Application class
 */
@HiltAndroidApp
class MiniApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createPrayerChannel()
    }

    private fun createPrayerChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                PRAYER_CHANNEL_ID,
                "Namoz vaqtlari",
                NotificationManager.IMPORTANCE_HIGH,
            ).apply { description = "Namoz vaqti eslatmalari" }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    companion object {
        const val PRAYER_CHANNEL_ID = "prayer_channel"
    }
}
