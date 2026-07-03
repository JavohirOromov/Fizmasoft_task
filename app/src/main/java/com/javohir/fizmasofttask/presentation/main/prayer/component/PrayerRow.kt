package com.javohir.fizmasofttask.presentation.main.prayer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javohir.fizmasofttask.core.ui.Green
import com.javohir.fizmasofttask.core.ui.GreenAccent
import com.javohir.fizmasofttask.core.ui.TextPrimary
import com.javohir.fizmasofttask.domain.model.PrayerTime

private val RowBg = Color(0xFFEEF3EC)

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer.component
 * Description: Bitta namoz qatori
 */
@Composable
fun PrayerRow(
    item: PrayerTime,
    isNext: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (isNext) GreenAccent else RowBg)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Green.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = prayerIcon(item.name),
                contentDescription = null,
                tint = Green,
                modifier = Modifier.size(22.dp),
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = item.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary,
        )
        if (isNext) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Keyingi",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Green)
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = item.time,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
        )
    }
}

private fun prayerIcon(name: String): ImageVector = when (name) {
    "Bomdod" -> Icons.Default.WbTwilight
    "Peshin" -> Icons.Default.LightMode
    "Asr" -> Icons.Default.WbSunny
    "Shom" -> Icons.Default.WbTwilight
    "Xufton" -> Icons.Default.Bedtime
    else -> Icons.Default.WbSunny
}
