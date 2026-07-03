package com.javohir.fizmasofttask.data.di

import com.javohir.fizmasofttask.data.repository.AuthRepositoryImpl
import com.javohir.fizmasofttask.data.repository.LocationRepositoryImpl
import com.javohir.fizmasofttask.data.repository.MovieRepositoryImpl
import com.javohir.fizmasofttask.data.repository.PrayerRepositoryImpl
import com.javohir.fizmasofttask.data.repository.WeatherRepositoryImpl
import com.javohir.fizmasofttask.domain.repository.AuthRepository
import com.javohir.fizmasofttask.domain.repository.LocationRepository
import com.javohir.fizmasofttask.domain.repository.MovieRepository
import com.javohir.fizmasofttask.domain.repository.PrayerRepository
import com.javohir.fizmasofttask.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.di
 * Description: Repository Dependency Injection
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindPrayerRepository(impl: PrayerRepositoryImpl): PrayerRepository
}
