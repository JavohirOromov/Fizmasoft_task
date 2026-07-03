package com.javohir.fizmasofttask.presentation.main.weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javohir.fizmasofttask.core.ui.TextPrimary
import com.javohir.fizmasofttask.core.ui.TextSecondary
import com.javohir.fizmasofttask.domain.model.HourlyWeather

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather.component
 * Description: Soatlik ob-havo karta
 */
@Composable
fun HourCard(hour: HourlyWeather) {
    Column(
        modifier = Modifier
            .width(58.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(StatBg)
            .padding(vertical = 12.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = hour.time, fontSize = 12.sp, color = TextSecondary)
        Icon(
            imageVector = weatherIcon(hour.conditionCode),
            contentDescription = null,
            tint = Gold,
            modifier = Modifier.size(22.dp),
        )
        Text(text = "${hour.temperature}°", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
    }
}
