package com.javohir.fizmasofttask.presentation.main.weather.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather.component
 * Description: Ob-havo ranglari
 */
internal val Gold = Color(0xFFE8A900)
internal val StatBg = Color(0xFFEEF3EC)

internal fun weatherIcon(code: Int): ImageVector = when (code) {
    0, 1 -> Icons.Default.WbSunny
    2, 3, 45, 48 -> Icons.Default.Cloud
    in 51..67 -> Icons.Default.Umbrella
    in 71..77 -> Icons.Default.AcUnit
    in 80..86 -> Icons.Default.Umbrella
    in 95..99 -> Icons.Default.Thunderstorm
    else -> Icons.Default.Cloud
}
