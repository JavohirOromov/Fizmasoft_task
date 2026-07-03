package com.javohir.fizmasofttask.presentation.main.map

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.javohir.fizmasofttask.core.ui.Background
import com.javohir.fizmasofttask.core.ui.Green
import com.javohir.fizmasofttask.core.ui.TextPrimary
import com.javohir.fizmasofttask.core.ui.TextSecondary

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.map
 * Description: Xarita tab
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
    ) { granted ->
        viewModel.onAction(MapIntent.PermissionResult(granted))
    }

    LaunchedEffect(Unit) {
        if (permissionState.status.isGranted) {
            viewModel.onAction(MapIntent.PermissionResult(true))
        } else {
            permissionState.launchPermissionRequest()
        }
    }

    MapContent(state = state)
}

@SuppressLint("DefaultLocale")
@Composable
private fun MapContent(
    state: MapState,
) {
    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(state.location) {
        state.location?.let {
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 14f)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = state.hasLocationPermission),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = state.hasLocationPermission,
                zoomControlsEnabled = true,
            ),
        ) {
            state.location?.let { loc ->
                Marker(
                    state = rememberMarkerState(
                        key = "${loc.latitude},${loc.longitude}",
                        position = LatLng(loc.latitude, loc.longitude),
                    ),
                    title = "Joriy joylashuv",
                )
            }
        }
        state.location?.let { loc ->
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Background),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFCFE9D6)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = null,
                            tint = Green,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(text = "Joriy joylashuv", fontSize = 13.sp, color = TextSecondary)
                        Text(
                            text = String.format("%.4f, %.4f", loc.latitude, loc.longitude),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary,
                        )
                    }
                }
            }
        }
    }
}
