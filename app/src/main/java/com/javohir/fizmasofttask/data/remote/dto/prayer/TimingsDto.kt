package com.javohir.fizmasofttask.data.remote.dto.prayer

import com.google.gson.annotations.SerializedName

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.dto.prayer
 * Description: 
 */
data class TimingsDto(
    @SerializedName("Fajr") val fajr: String,
    @SerializedName("Dhuhr") val dhuhr: String,
    @SerializedName("Asr") val asr: String,
    @SerializedName("Maghrib") val maghrib: String,
    @SerializedName("Isha") val isha: String,
)
