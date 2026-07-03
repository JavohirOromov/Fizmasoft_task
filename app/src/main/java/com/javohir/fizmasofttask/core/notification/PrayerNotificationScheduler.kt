package com.javohir.fizmasofttask.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.javohir.fizmasofttask.domain.model.PrayerTime
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.core.notification
 * Description: Har namoz vaqtiga AlarmManager orqali eslatma rejalash
 */
class PrayerNotificationScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun schedule(times: List<PrayerTime>) {
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        val today = LocalDate.now()
        val now = LocalDateTime.now()

        times.forEachIndexed { index, prayer ->
            val time = runCatching { LocalTime.parse(prayer.time) }.getOrNull() ?: return@forEachIndexed
            val dateTime = LocalDateTime.of(today, time)
            if (dateTime.isBefore(now)) return@forEachIndexed

            val triggerAt = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            val pendingIntent = buildPendingIntent(index, prayer)

            try {
                if (canScheduleExact(alarmManager)) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
                } else {
                    alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
                }
            } catch (e: SecurityException) {
                alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
            }
        }
    }

    private fun canScheduleExact(am: AlarmManager): Boolean =
        Build.VERSION.SDK_INT < Build.VERSION_CODES.S || am.canScheduleExactAlarms()

    private fun buildPendingIntent(index: Int, prayer: PrayerTime): PendingIntent {
        val intent = Intent(context, PrayerAlarmReceiver::class.java).apply {
            putExtra(PrayerAlarmReceiver.EXTRA_NAME, prayer.name)
            putExtra(PrayerAlarmReceiver.EXTRA_TIME, prayer.time)
        }
        return PendingIntent.getBroadcast(
            context,
            index,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }
}
