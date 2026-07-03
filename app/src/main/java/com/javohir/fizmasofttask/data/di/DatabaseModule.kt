package com.javohir.fizmasofttask.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.javohir.fizmasofttask.data.local.dataBase.AppDatabase
import com.javohir.fizmasofttask.data.local.movies.MovieDao
import com.javohir.fizmasofttask.data.local.prayer.PrayerDao
import com.javohir.fizmasofttask.data.local.weather.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.di
 * Description: Database / local storage Dependency Injection
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences("fizmasoft_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "fizmasoft.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao = db.movieDao()

    @Provides
    fun provideWeatherDao(db: AppDatabase): WeatherDao = db.weatherDao()

    @Provides
    fun providePrayerDao(db: AppDatabase): PrayerDao = db.prayerDao()
}
