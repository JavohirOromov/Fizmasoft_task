package com.javohir.fizmasofttask.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.splash
 * Description: ViewModel
 */
@HiltViewModel
class SplashViewModel @Inject constructor(

): ViewModel() {

    private val _destination = MutableStateFlow<SplashDestination?>(null)
    val destination = _destination.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1500.milliseconds)

            // TODO: auth qilingan bo'lsa MAIN ga, aks holda LOGIN ga o'tsin.
            //  Hozircha doim LOGIN ga o'tadi.
            val isAuthenticated = false
            _destination.value = if (isAuthenticated) {
                SplashDestination.MAIN
            } else {
                SplashDestination.LOGIN
            }
        }
    }

}