package com.javohir.fizmasofttask.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.fizmasofttask.domain.usecase.ValidateBirthDateUseCase
import com.javohir.fizmasofttask.domain.usecase.ValidatePassportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.auth.login
 * Description: ViewModel
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateBirthDate: ValidateBirthDateUseCase,
    private val validatePassport: ValidatePassportUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(value = LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()

    fun onAction(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.BirthDataChanged -> {
                val formatted = formatBirthDate(input = intent.value)
                _state.update { it.copy(birthData = formatted) }
                validate()
            }
            is LoginIntent.PassportChanged -> {
                _state.update { it.copy(passport = intent.value.uppercase()) }
                validate()
            }
            is LoginIntent.TogglePassportVisibility -> {
                _state.update { it.copy(isPassportVisible = !it.isPassportVisible) }
            }
            is LoginIntent.LoginClicked -> login()
        }
    }

    private fun formatBirthDate(input: String): String =
        input.filter { it.isDigit() }.take(8)

    private fun validate() {
        val s = state.value
        _state.update {
            it.copy(isValid = validatePassport(s.passport) && validateBirthDate(s.birthData))
        }
    }

    private fun login() {
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigateToFaceDetection)
        }
    }
}