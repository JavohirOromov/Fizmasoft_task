package com.javohir.fizmasofttask.data.local.prayer

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javohir.fizmasofttask.domain.model.PrayerTime

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.local.prayer
 * Description: Namoz vaqtlari cache (bitta qator, id = 0)
 */
@Entity(tableName = "prayer")
data class PrayerEntity(
    @PrimaryKey val id: Int = 0,
    val date: String,
    val times: List<PrayerTime>,
)
