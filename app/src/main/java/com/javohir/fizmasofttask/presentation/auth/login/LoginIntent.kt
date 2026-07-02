package com.javohir.fizmasofttask.presentation.auth.login

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.login
 * Description: User Action
 */
sealed class LoginIntent {
    data class BirthDataChanged(val value: String): LoginIntent()
    data class PassportChanged(val value: String): LoginIntent()
    object TogglePassportVisibility: LoginIntent()
    object LoginClicked: LoginIntent()
}