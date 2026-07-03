package com.javohir.fizmasofttask.data.di

import com.javohir.fizmasofttask.data.remote.api.movies.MovieApi
import com.javohir.fizmasofttask.data.remote.api.weather.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.di
 * Description: Network Dependency Injection 
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TMDB_API_KEY = "18549e90963cc68c3bfb79900f0611b9"
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply { level =
            HttpLoggingInterceptor.Level.BASIC }
        val apiKey = Interceptor { chain ->
            val original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("api_key", TMDB_API_KEY).build()
            chain.proceed(original.newBuilder().url(url).build())
        }
        return OkHttpClient.Builder()
            .addInterceptor(apiKey)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    // Open-Meteo — alohida Retrofit (boshqa base URL, api_key yo'q)
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi =
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
}