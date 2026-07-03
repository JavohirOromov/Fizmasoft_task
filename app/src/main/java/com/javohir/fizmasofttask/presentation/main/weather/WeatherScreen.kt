package com.javohir.fizmasofttask.presentation.main.weather

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.javohir.fizmasofttask.core.ui.Background
import com.javohir.fizmasofttask.core.ui.Green
import com.javohir.fizmasofttask.core.ui.TextDisabled
import com.javohir.fizmasofttask.core.ui.TextPrimary
import com.javohir.fizmasofttask.core.ui.TextSecondary
import com.javohir.fizmasofttask.domain.model.HourlyWeather

private val Gold = Color(0xFFE8A900)
private val StatBg = Color(0xFFEEF3EC)

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.weather
 * Description: Ob-havo
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
    ) { granted ->
        viewModel.onAction(WeatherIntent.PermissionResult(granted))
    }

    LaunchedEffect(Unit) {
        if (permissionState.status.isGranted) {
            viewModel.onAction(WeatherIntent.PermissionResult(true))
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is WeatherEvent.ShowError ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    WeatherContent(state = state)
}

@Composable
private fun WeatherContent(
    state: WeatherState,
) {
    val weather = state.weather

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        when (weather) {
            null if state.isLoading ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            null ->
                Text(
                    text = "Ma'lumot yo'q",
                    color = TextSecondary,
                    modifier = Modifier.align(Alignment.Center),
                )

            else -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = weatherIcon(weather.conditionCode),
                    contentDescription = null,
                    tint = Gold,
                    modifier = Modifier.size(68.dp),
                )
                Text(
                    text = "${weather.temperature}°",
                    fontSize = 74.sp,
                    fontWeight = FontWeight.Light,
                    color = TextPrimary,
                )
                Text(text = weather.condition, fontSize = 16.sp, color = TextSecondary)
                Text(
                    text = "Eng yuqori ${weather.high}° · Eng past ${weather.low}°",
                    fontSize = 14.sp,
                    color = TextDisabled,
                )

                Spacer(modifier = Modifier.height(26.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    StatCard(
                        icon = Icons.Default.WaterDrop,
                        value = "${weather.humidity}%",
                        label = "Namlik",
                        modifier = Modifier.weight(1f),
                    )
                    StatCard(
                        icon = Icons.Default.Air,
                        value = "${weather.windSpeed} m/s",
                        label = "Shamol",
                        modifier = Modifier.weight(1f),
                    )
                    StatCard(
                        icon = Icons.Default.Thermostat,
                        value = "${weather.feelsLike}°",
                        label = "His etiladi",
                        modifier = Modifier.weight(1f),
                    )
                }

                if (weather.hourly.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(26.dp))
                    Text(
                        text = "Bugun · soatlik",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                    )
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(weather.hourly) { hour -> HourCard(hour) }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(
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

@Composable
private fun HourCard(hour: HourlyWeather) {
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

private fun weatherIcon(code: Int): ImageVector = when (code) {
    0, 1 -> Icons.Default.WbSunny
    2, 3, 45, 48 -> Icons.Default.Cloud
    in 51..67 -> Icons.Default.Umbrella
    in 71..77 -> Icons.Default.AcUnit
    in 80..86 -> Icons.Default.Umbrella
    in 95..99 -> Icons.Default.Thunderstorm
    else -> Icons.Default.Cloud
}
