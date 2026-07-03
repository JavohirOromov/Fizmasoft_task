package com.javohir.fizmasofttask.presentation.main.prayer

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.javohir.fizmasofttask.core.ui.Background
import com.javohir.fizmasofttask.core.ui.TextSecondary
import com.javohir.fizmasofttask.presentation.main.prayer.component.HeaderCard
import com.javohir.fizmasofttask.presentation.main.prayer.component.PrayerRow
import androidx.core.net.toUri

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.prayer
 * Description: Namoz vaqtlari tab
 */
@SuppressLint("InlinedApi", "BatteryLife")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PrayerScreen(
    viewModel: PrayerViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val supportsPrayer = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    val locationPermission = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
    ) { granted ->
        if (supportsPrayer) viewModel.onAction(PrayerIntent.PermissionResult(granted))
    }
    val notificationPermission = rememberPermissionState(
        permission = Manifest.permission.POST_NOTIFICATIONS,
    )

    LaunchedEffect(Unit) {
        if (locationPermission.status.isGranted) {
            if (supportsPrayer) viewModel.onAction(PrayerIntent.PermissionResult(true))
        } else {
            locationPermission.launchPermissionRequest()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            !notificationPermission.status.isGranted
        ) {
            notificationPermission.launchPermissionRequest()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is PrayerEvent.ShowError ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    PrayerContent(state = state)
}

@Composable
private fun PrayerContent(
    state: PrayerState,
) {
    val prayerTimes = state.prayerTimes

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        when (prayerTimes) {
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
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                HeaderCard(
                    date = prayerTimes.date,
                    countdown = state.countdown,
                    nextName = state.nextPrayerName,
                    nextTime = prayerTimes.times
                        .firstOrNull { it.name == state.nextPrayerName }?.time,
                )

                prayerTimes.times.forEach { prayer ->
                    PrayerRow(
                        item = prayer,
                        isNext = prayer.name == state.nextPrayerName,
                    )
                }
            }
        }
    }
}
