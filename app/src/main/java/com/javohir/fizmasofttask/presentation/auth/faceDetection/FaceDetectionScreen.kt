package com.javohir.fizmasofttask.presentation.auth.faceDetection

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageProxy
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.javohir.fizmasofttask.core.ui.CameraBg
import com.javohir.fizmasofttask.core.ui.GreenAccent
import com.javohir.fizmasofttask.core.ui.Scrim
import com.javohir.fizmasofttask.presentation.auth.faceDetection.component.CameraPreview
import com.javohir.fizmasofttask.presentation.auth.faceDetection.utils.FaceStatus

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.faceDetection
 * Description: Composable Screen
 */

@Composable
fun FaceDetectionScreen(
    paddingValues: PaddingValues,
    navigateToMain: () -> Unit,
    viewModel: FaceDetectionViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { granted ->
        viewModel.onAction(FaceDetectionIntent.PermissionResult(granted))
    }

    LaunchedEffect(Unit) {
        val granted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA,
        ) == PackageManager.PERMISSION_GRANTED

        if (granted)
            viewModel.onAction(FaceDetectionIntent.PermissionResult(true))
        else permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                FaceDetectionEvent.NavigateToMain -> navigateToMain()
            }
        }
    }

    FaceDetectionContent(
        paddingValues = paddingValues,
        state = state,
        onFrame = viewModel::onFrame,
    )
}

@Composable
private fun FaceDetectionContent(
    paddingValues: PaddingValues,
    state: FaceDetectionState,
    onFrame: (ImageProxy) -> Unit,
) {
    val transition = rememberInfiniteTransition(label = "face")
    val pulse by transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2200), RepeatMode.Reverse),
        label = "pulse",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CameraBg),
    ) {

        if (state.hasCameraPermission) {
            CameraPreview(
                onFrame = onFrame,
                modifier = Modifier.fillMaxSize(),
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen),
        ) {
            val ovalW = 244.dp.toPx()
            val ovalH = 320.dp.toPx()
            val cx = size.width / 2f
            val cy = size.height * 0.44f
            val topLeft = Offset(cx - ovalW / 2f, cy - ovalH / 2f)
            val ovalSize = Size(ovalW, ovalH)

            drawRect(color = Scrim)

            drawOval(
                color = Color.Transparent,
                topLeft = topLeft, size = ovalSize,
                blendMode = BlendMode.Clear,
            )
            drawOval(
                color = lerp(Color.White.copy(alpha = 0.9f), GreenAccent, pulse),
                topLeft = topLeft, size = ovalSize,
                style = Stroke(width = 3.dp.toPx()),
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(46.dp))
            Text(
                text = when (state.status) {
                    FaceStatus.SEARCHING -> "Yuz aniqlanmoqda"
                    FaceStatus.DETECTED -> "Yuz aniqlandi, harakatsiz turing"
                    FaceStatus.PASSED -> "Tayyor"
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(bottom = 48.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                tint = GreenAccent.copy(alpha = 0.95f),
                modifier = Modifier.size(30.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (state.hasCameraPermission)
                    "Yuzingizni oval ichida joylashtiring"
                else
                    "Davom etish uchun kamera ruxsatini bering",
                fontSize = 15.sp,
                color = Color.White,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FaceDetectionPreview() {
    FaceDetectionContent(
        paddingValues = PaddingValues(all = 12.dp),
        state = FaceDetectionState(
            hasCameraPermission = false,
            status = FaceStatus.SEARCHING,
        ),
        onFrame = {},
    )
}