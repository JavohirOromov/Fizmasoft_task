package com.javohir.fizmasofttask.domain.repository

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.repository
 * Description: Local auth holati (SharedPreferences)
 */
interface AuthRepository {
    fun isAuthenticated(): Boolean
    fun setAuthenticated(value: Boolean)
}