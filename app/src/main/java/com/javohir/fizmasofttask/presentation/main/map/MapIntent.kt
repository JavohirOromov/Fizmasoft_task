package com.javohir.fizmasofttask.presentation.main.map

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.map
 * Description: User Action
 */
sealed class MapIntent {
    data class PermissionResult(val granted: Boolean) : MapIntent()
}