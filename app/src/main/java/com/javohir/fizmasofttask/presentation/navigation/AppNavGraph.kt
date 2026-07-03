package com.javohir.fizmasofttask.presentation.navigation
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.javohir.fizmasofttask.presentation.auth.faceDetection.FaceDetectionScreen
import com.javohir.fizmasofttask.presentation.auth.login.LoginScreen
import com.javohir.fizmasofttask.presentation.main.MainScreen
import com.javohir.fizmasofttask.presentation.splash.utils.SplashDestination
import com.javohir.fizmasofttask.presentation.splash.SplashScreen

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
        composable(Routes.SPLASH){
            SplashScreen(
                paddingValues = paddingValues,
                onNavigate = { destination ->
                    when (destination){
                        SplashDestination.LOGIN -> navController.navigate(Routes.LOGIN){
                            popUpTo(Routes.SPLASH){inclusive = true}
                        }
                        SplashDestination.MAIN -> navController.navigate(Routes.MAIN){
                            popUpTo(Routes.SPLASH){inclusive = true}
                        }
                    }
                }
            )
        }
        composable(Routes.LOGIN){
            LoginScreen(
                paddingValues = paddingValues,
                navigateToFaceDetection = {
                    navController.navigate(Routes.FACE_DETECTION){
                        popUpTo(Routes.LOGIN){inclusive = false}
                    }
                }
            )
        }
        composable(Routes.FACE_DETECTION){
            FaceDetectionScreen(
                paddingValues = paddingValues,
                navigateToMain = {
                    navController.navigate(Routes.MAIN){
                        popUpTo(Routes.LOGIN){inclusive = true}
                    }
                }
            )
        }
        composable(Routes.MAIN){
            MainScreen(
                paddingValues = paddingValues
            )
        }
    }
}