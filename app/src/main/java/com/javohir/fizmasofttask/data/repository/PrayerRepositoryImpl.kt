package com.javohir.fizmasofttask.data.repository

import com.javohir.fizmasofttask.data.local.mapper.prayerMapper.toDomain
import com.javohir.fizmasofttask.data.local.mapper.prayerMapper.toEntity
import com.javohir.fizmasofttask.data.local.prayer.PrayerDao
import com.javohir.fizmasofttask.data.remote.api.prayer.PrayerApi
import com.javohir.fizmasofttask.data.remote.mapper.prayerMapper.toDomain
import com.javohir.fizmasofttask.domain.model.PrayerTimes
import com.javohir.fizmasofttask.domain.repository.PrayerRepository
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.repository
 * Description: Sof data — Aladhan + Room cache
 */
class PrayerRepositoryImpl @Inject constructor(
    private val api: PrayerApi,
    private val dao: PrayerDao,
) : PrayerRepository {

    override suspend fun fetchRemote(latitude: Double, longitude: Double, date: String): PrayerTimes =
        api.getTimings(date, latitude, longitude).toDomain()

    override suspend fun getCached(): PrayerTimes? =
        dao.get()?.toDomain()

    override suspend fun cache(prayerTimes: PrayerTimes) =
        dao.upsert(prayerTimes.toEntity())
}
