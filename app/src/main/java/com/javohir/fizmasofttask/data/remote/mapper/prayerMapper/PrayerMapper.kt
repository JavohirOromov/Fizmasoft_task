package com.javohir.fizmasofttask.data.remote.mapper.prayerMapper

import com.javohir.fizmasofttask.data.remote.dto.prayer.PrayerResponseDto
import com.javohir.fizmasofttask.domain.model.PrayerTime
import com.javohir.fizmasofttask.domain.model.PrayerTimes

/**
 * Created by: Javohir Oromov macOS
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.mapper.prayerMapper
 * Description: Mapper
 */

fun PrayerResponseDto.toDomain(): PrayerTimes {
    val t = data.timings
    return PrayerTimes(
        date = data.date.readable,
        times = listOf(
            PrayerTime("Bomdod", t.fajr.clean()),
            PrayerTime("Peshin", t.dhuhr.clean()),
            PrayerTime("Asr", t.asr.clean()),
            PrayerTime("Shom", t.maghrib.clean()),
            PrayerTime("Xufton", t.isha.clean()),
        ),
    )
}

private fun String.clean(): String = substringBefore(" ").trim()