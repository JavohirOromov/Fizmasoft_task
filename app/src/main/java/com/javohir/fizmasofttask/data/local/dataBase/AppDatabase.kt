package com.javohir.fizmasofttask.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.javohir.fizmasofttask.data.local.movies.MovieDao
import com.javohir.fizmasofttask.data.local.movies.MovieEntity
import com.javohir.fizmasofttask.data.local.prayer.PrayerDao
import com.javohir.fizmasofttask.data.local.prayer.PrayerEntity
import com.javohir.fizmasofttask.data.local.weather.WeatherDao
import com.javohir.fizmasofttask.data.local.weather.WeatherEntity

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local
 * Description: DataBase
 */
@Database(
    entities = [MovieEntity::class, WeatherEntity::class, PrayerEntity::class],
    version = 4,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun weatherDao(): WeatherDao
    abstract fun prayerDao(): PrayerDao
}
