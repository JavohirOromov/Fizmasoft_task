package com.javohir.fizmasofttask.data.local.prayer

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PrayerDao {

    @Query("SELECT * FROM prayer WHERE id = 0")
    suspend fun get(): PrayerEntity?

    @Upsert
    suspend fun upsert(prayer: PrayerEntity)
}
