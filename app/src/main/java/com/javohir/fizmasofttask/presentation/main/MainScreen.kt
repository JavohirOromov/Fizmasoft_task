package com.javohir.fizmasofttask.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.javohir.fizmasofttask.core.ui.Background
import com.javohir.fizmasofttask.core.ui.TextPrimary
import com.javohir.fizmasofttask.presentation.main.component.MainBottomBar
import com.javohir.fizmasofttask.presentation.main.map.MapScreen
import com.javohir.fizmasofttask.presentation.main.movies.MoviesScreen
import com.javohir.fizmasofttask.presentation.main.prayer.PrayerScreen
import com.javohir.fizmasofttask.presentation.main.weather.WeatherScreen

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main
 * Description: Main konteyner — TopBar + BottomBar + ichki NavHost
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    paddingValues: PaddingValues,
) {
    val tabNavController = rememberNavController()

    val backStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val currentTab = MainTab.entries.find { it.route == currentRoute } ?: MainTab.Movies

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentTab.label,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background),
            )
        },
        bottomBar = { MainBottomBar(navController = tabNavController) },
    ) { innerPadding ->
        NavHost(
            navController = tabNavController,
            startDestination = MainTab.Movies.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            composable(MainTab.Movies.route) { MoviesScreen() }
            composable(MainTab.Map.route) { MapScreen() }
            composable(MainTab.Prayer.route) { PrayerScreen() }
            composable(MainTab.Weather.route) { WeatherScreen() }
        }
    }
}
