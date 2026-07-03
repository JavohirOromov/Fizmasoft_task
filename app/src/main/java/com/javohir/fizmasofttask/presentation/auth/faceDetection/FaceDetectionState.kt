package com.javohir.fizmasofttask.presentation.auth.faceDetection

import com.javohir.fizmasofttask.presentation.auth.faceDetection.utils.FaceStatus

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.faceDetection
 * Description: UI state
 */
data class FaceDetectionState(
    val hasCameraPermission: Boolean = false,
    val status: FaceStatus = FaceStatus.SEARCHING
)
