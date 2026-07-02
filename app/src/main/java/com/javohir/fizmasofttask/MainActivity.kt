package com.javohir.fizmasofttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.javohir.fizmasofttask.core.ui.FizmasoftTaskTheme
import com.javohir.fizmasofttask.presentation.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        @Suppress("DEPRECATION")
        window.navigationBarColor = Color.Transparent.toArgb()
        window.statusBarColor = Color.Transparent.toArgb()
        WindowInsetsControllerCompat(window, window.decorView).run {
            isAppearanceLightNavigationBars = false
            isAppearanceLightStatusBars = true
        }

        enableEdgeToEdge()
        setContent {
            FizmasoftTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavGraph(
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}