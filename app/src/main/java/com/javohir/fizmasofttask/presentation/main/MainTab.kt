package com.javohir.fizmasofttask.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main
 * Description: Main App tablari
 */
enum class MainTab(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    Movies("movies", "Kinolar", Icons.Default.Movie),
    Map("map", "Xarita", Icons.Default.Map),
    Prayer("prayer", "Namoz", Icons.Default.Schedule),
    Weather("weather", "Ob-havo", Icons.Default.Cloud),
}