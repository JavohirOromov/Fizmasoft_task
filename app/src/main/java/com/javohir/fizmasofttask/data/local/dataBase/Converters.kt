package com.javohir.fizmasofttask.data.local.dataBase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.javohir.fizmasofttask.domain.model.HourlyWeather

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local.dataBase
 * Description: Room TypeConverter
 */
class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromHourlyList(list: List<HourlyWeather>): String = gson.toJson(list)

    @TypeConverter
    fun toHourlyList(json: String): List<HourlyWeather> {
        val type = object : TypeToken<List<HourlyWeather>>() {}.type
        return gson.fromJson(json, type)
    }
}
