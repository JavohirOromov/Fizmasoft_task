package com.javohir.fizmasofttask.core.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.javohir.fizmasofttask.R
import com.javohir.fizmasofttask.app.MiniApp

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.core.notification
 * Description: Alarm otganда bildirishnoma ko'rsatadi
 */
class PrayerAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val name = intent.getStringExtra(EXTRA_NAME) ?: "Namoz"
        val time = intent.getStringExtra(EXTRA_TIME).orEmpty()

        val notification = NotificationCompat.Builder(context, MiniApp.PRAYER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("$name vaqti kirdi")
            .setContentText(if (time.isNotEmpty()) "Vaqt: $time" else "Namoz vaqti")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val manager = NotificationManagerCompat.from(context)
        if (manager.areNotificationsEnabled()) {
            manager.notify(name.hashCode(), notification)
        }
    }

    companion object {
        const val EXTRA_NAME = "prayer_name"
        const val EXTRA_TIME = "prayer_time"
    }
}
