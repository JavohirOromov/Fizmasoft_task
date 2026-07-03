package com.javohir.fizmasofttask.data.remote.dto.prayer

import com.google.gson.annotations.SerializedName

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.remote.dto.prayer
 * Description: Response class
 */
data class PrayerResponseDto(
    @SerializedName("data") val data: PrayerDataDto,
)