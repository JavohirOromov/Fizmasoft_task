package com.javohir.fizmasofttask.presentation.auth.faceDetection

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.faceDetection
 * Description: User Action
 */
sealed class FaceDetectionIntent {
    data class PermissionResult(val granted: Boolean): FaceDetectionIntent()
    data class FaceDetected(val present: Boolean): FaceDetectionIntent()
}