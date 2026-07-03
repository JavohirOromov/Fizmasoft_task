package com.javohir.fizmasofttask.core.detector

import androidx.camera.core.ImageProxy

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.core.detector
 * Description: Yuz aniqlash
 */

interface FaceDetector{
    fun analyze(imageProxy: ImageProxy, onResult: (faceDetection: Boolean) -> Unit)
    fun close()
}