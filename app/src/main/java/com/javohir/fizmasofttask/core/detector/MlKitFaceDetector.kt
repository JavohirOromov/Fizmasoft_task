package com.javohir.fizmasofttask.core.detector

import android.graphics.Rect
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.core.detector
 * Description: ML Kit orqali yuz aniqlash
 */
class MlKitFaceDetector @Inject constructor(): FaceDetector {

    private val detector = FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setMinFaceSize(0.15f)
            .build()
    )

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy, onResult: (faceDetection: Boolean) -> Unit) {
        val mediaImage = imageProxy.image
        if (mediaImage == null){
            imageProxy.close()
            onResult(false)
            return
        }
        val rotation = imageProxy.imageInfo.rotationDegrees
        val rotated = rotation == 90 || rotation == 270
        val imageW = if (rotated) imageProxy.height else imageProxy.width
        val imageH = if (rotated) imageProxy.width else imageProxy.height

        val image = InputImage.fromMediaImage(mediaImage, rotation)
        detector.process(image)
            .addOnSuccessListener { faces ->
                val ok = faces
                    .maxByOrNull { it.boundingBox.width().toLong() * it.boundingBox.height() }
                    ?.let { isInsideGuide(it.boundingBox, imageW, imageH) }
                    ?: false
                onResult(ok)
            }

            .addOnFailureListener { onResult(false) }
            .addOnCompleteListener { imageProxy.close() }
    }

    private fun isInsideGuide(box: Rect, imageW: Int, imageH: Int): Boolean {
        if (imageW == 0 || imageH == 0) return false

        val left = box.left.toFloat() / imageW
        val right = box.right.toFloat() / imageW
        val top = box.top.toFloat() / imageH
        val bottom = box.bottom.toFloat() / imageH
        val widthFraction = box.width().toFloat() / imageW

        val insideBounds = left >= 0.20f && right <= 0.80f &&
            top >= 0.12f && bottom <= 0.80f

        val goodSize = widthFraction in 0.30f..0.55f

        return insideBounds && goodSize
    }

    override fun close() {
       detector.close()
    }

}
