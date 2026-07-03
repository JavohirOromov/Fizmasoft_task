package com.javohir.fizmasofttask.presentation.main.weather.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javohir.fizmasofttask.core.ui.Green
import com.javohir.fizmasofttask.core.ui.TextPrimary
import com.javohir.fizmasofttask.core.ui.TextSecondary

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather.component
 * Description: Ob-havo stat karta
 */
@Composable
fun StatCard(
    icon: ImageVector,
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(StatBg)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Green, modifier = Modifier.size(24.dp))
        Text(text = value, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Text(text = label, fontSize = 12.sp, color = TextSecondary)
    }
}
