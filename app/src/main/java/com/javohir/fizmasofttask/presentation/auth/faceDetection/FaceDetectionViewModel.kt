package com.javohir.fizmasofttask.presentation.auth.faceDetection

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.fizmasofttask.core.detector.FaceDetector
import com.javohir.fizmasofttask.domain.repository.AuthRepository
import com.javohir.fizmasofttask.presentation.auth.faceDetection.utils.FaceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.faceDetection
 * Description: ViewModel
 */
@HiltViewModel
class FaceDetectionViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val faceDetector: FaceDetector
): ViewModel() {

    private val _state = MutableStateFlow(FaceDetectionState())
    val state: StateFlow<FaceDetectionState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<FaceDetectionEvent>()
    val event = _event.asSharedFlow()

    private var stableJob: Job? = null

    fun onFrame(imageProxy: ImageProxy){
        faceDetector.analyze(imageProxy){ detected ->
            onAction(FaceDetectionIntent.FaceDetected(detected))
        }
    }
    fun onAction(intent: FaceDetectionIntent){
        when(intent){
            is FaceDetectionIntent.PermissionResult -> {
                _state.update { it.copy(hasCameraPermission = intent.granted) }
            }
            is FaceDetectionIntent.FaceDetected -> {
                handleFace(intent.present)
            }
        }
    }

    private fun handleFace(present: Boolean){
        if (_state.value.status == FaceStatus.PASSED) return

        if (present) {
            if (stableJob == null) {
                _state.update { it.copy(status = FaceStatus.DETECTED) }
                stableJob = viewModelScope.launch {
                    delay(1500.milliseconds)
                    _state.update { it.copy(status = FaceStatus.PASSED) }
                    authRepository.setAuthenticated(true)
                    _event.emit(FaceDetectionEvent.NavigateToMain)
                }
            }
        } else {
            stableJob?.cancel()
            stableJob = null
            _state.update { it.copy(status = FaceStatus.SEARCHING) }
        }
    }

    override fun onCleared() {
        faceDetector.close()
    }
}