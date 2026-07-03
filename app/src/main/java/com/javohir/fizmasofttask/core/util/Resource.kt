package com.javohir.fizmasofttask.core.util

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.core.util
 * Description: API holati 
 */
sealed interface Resource<out T> {
    data object Loading: Resource<Nothing>
    data class Success<T>(val data: T): Resource<T>
    data class Error(val message: String): Resource<Nothing>
}