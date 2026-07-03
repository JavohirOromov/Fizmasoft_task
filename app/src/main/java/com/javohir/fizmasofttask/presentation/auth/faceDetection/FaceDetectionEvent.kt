package com.javohir.fizmasofttask.presentation.auth.faceDetection

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.faceDetection
 * Description: Event's
 */
sealed class FaceDetectionEvent {
    data object NavigateToMain: FaceDetectionEvent()
}