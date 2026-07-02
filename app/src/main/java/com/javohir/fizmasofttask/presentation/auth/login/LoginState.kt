package com.javohir.fizmasofttask.presentation.auth.login

/**
 * Created by: Javohir Oromov macOS
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.login
 * Description: UI State
 */
data class LoginState(
    val birthData: String = "",
    val passport: String = "",
    val isPassportVisible: Boolean = false,
    val isValid: Boolean = false,
    val error: String? = null,
)