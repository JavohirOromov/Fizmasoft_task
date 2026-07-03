package com.javohir.fizmasofttask.data.local.weather

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE id = 0")
    suspend fun get(): WeatherEntity?

    @Upsert
    suspend fun upsert(weather: WeatherEntity)
}
