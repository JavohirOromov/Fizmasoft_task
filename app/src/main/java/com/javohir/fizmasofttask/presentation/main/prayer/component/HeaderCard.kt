package com.javohir.fizmasofttask.presentation.main.prayer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javohir.fizmasofttask.core.ui.Green

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer.component
 * Description: Namoz header
 */
@Composable
fun HeaderCard(
    date: String,
    countdown: String?,
    nextName: String?,
    nextTime: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(Green)
            .padding(20.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Joriy joylashuv", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = date, fontSize = 13.sp, color = Color.White.copy(alpha = 0.8f))
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "Keyingi namozgacha", fontSize = 13.sp, color = Color.White.copy(alpha = 0.82f))
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = countdown ?: "—",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
        )
        if (nextName != null && nextTime != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$nextName · $nextTime",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.85f),
            )
        }
    }
}
