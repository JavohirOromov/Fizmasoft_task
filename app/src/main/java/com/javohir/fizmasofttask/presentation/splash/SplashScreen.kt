package com.javohir.fizmasofttask.presentation.splash
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.splash
 * Description: Composable screen
 */

@Composable
fun SplashScreen(
    paddingValues: PaddingValues,
    onNavigate: (SplashDestination) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
){
    val destination by viewModel.destination.collectAsStateWithLifecycle()

    LaunchedEffect(destination) {
        destination?.let { onNavigate }
    }
    
}