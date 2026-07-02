package com.javohir.fizmasofttask.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.navigation
 * Description: App Navigation
 */
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues,
){

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ){

    }
}