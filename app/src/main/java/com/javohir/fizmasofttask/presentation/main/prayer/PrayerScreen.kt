package com.javohir.fizmasofttask.presentation.main.prayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.javohir.fizmasofttask.core.ui.Background
import com.javohir.fizmasofttask.core.ui.TextPrimary

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer
 * Description: Namoz vaqtlari tab (placeholder)
 */
@Composable
fun PrayerScreen() {
    Box(
        modifier = Modifier.fillMaxSize().background(Background),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Namoz vaqtlari", fontSize = 22.sp, color = TextPrimary)
    }
}