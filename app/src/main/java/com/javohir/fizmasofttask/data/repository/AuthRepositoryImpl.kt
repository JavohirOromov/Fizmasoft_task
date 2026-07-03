package com.javohir.fizmasofttask.data.repository

import android.content.SharedPreferences
import com.javohir.fizmasofttask.domain.repository.AuthRepository
import javax.inject.Inject
import androidx.core.content.edit

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.data.repository
 * Description: AuthRepository impl — SharedPreferences
 */
class AuthRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences,
) : AuthRepository {

    override fun isAuthenticated(): Boolean =
        prefs.getBoolean(KEY_AUTH, false)

    override fun setAuthenticated(value: Boolean) {
        prefs.edit { putBoolean(KEY_AUTH, value) }
    }

    companion object {
        private const val KEY_AUTH = "is_authenticated"
    }
}