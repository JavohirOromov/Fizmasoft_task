package com.javohir.fizmasofttask.data.local.mapper.prayerMapper

import com.javohir.fizmasofttask.data.local.prayer.PrayerEntity
import com.javohir.fizmasofttask.domain.model.PrayerTimes

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local.mapper.prayerMapper
 * Description: PrayerEntity <-> PrayerTimes (domain)
 */
fun PrayerEntity.toDomain(): PrayerTimes = PrayerTimes(date = date, times = times)

fun PrayerTimes.toEntity(): PrayerEntity = PrayerEntity(id = 0, date = date, times = times)
