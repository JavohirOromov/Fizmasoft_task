package com.javohir.fizmasofttask.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(value = LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()


    fun onAction(intent: LoginIntent){

        when(intent){
            is LoginIntent.BirthDataChanged -> {
                val formatted = formatBirthDate(input = intent.value)
                _state.update {
                    it.copy(birthData = formatted)
                }
                validate()
            }
            is LoginIntent.PassportChanged -> {
                val upper = intent.value.uppercase()
                _state.update {
                    it.copy(passport = upper)
                }
                validate()
            }
            is LoginIntent.TogglePassportVisibility -> {
                _state.update {
                    it.copy(
                        isPassportVisible = !it.isPassportVisible
                    )
                }
            }
            is LoginIntent.LoginClicked -> {
                login()
            }
        }
    }
    private fun formatBirthDate(input: String): String {
        return input.filter { it.isDigit() }.take(8)
    }
    private fun validate(){
        val state = state.value
        val isBirthValid = isValidBirthDate(state.birthData)
        val isPassportValid = isValidPassport(state.passport)

        _state.update {
            it.copy(isValid = isPassportValid && isBirthValid)
        }
    }

    private fun isValidBirthDate(date: String): Boolean {
        if (date.length != 8) return false

        val day = date.substring(0, 2).toIntOrNull() ?: return false
        val month = date.substring(2, 4).toIntOrNull() ?: return false
        val year = date.substring(4, 8).toIntOrNull() ?: return false

        if (year !in 1900..2012) return false
        if (month !in 1..12) return false
        if (day !in 1..31) return false

        val daysInMonth = when (month) {
            2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }
        return day <= daysInMonth
    }

    private fun isValidPassport(passport: String): Boolean {
        if (passport.length != 9) return false
        if (!passport.matches(Regex("^[A-Z]{2}\\d{7}$"))) return false

        val validSeries = listOf(
            "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH", "AK", "AL",
            "AM", "AN", "AO", "AP", "AQ", "AR", "AS", "AT", "AU", "AV",
            "AW", "AX", "AY", "AZ", "BA", "BB", "BC", "BD", "BE", "BF",
            "BG", "BH", "BI", "BK", "BL", "BM", "BN", "BO", "BP", "BQ",
            "BR", "BS", "BT", "BU", "BV", "BW", "BX", "BY", "BZ"
        )

        return passport.take(2) in validSeries
    }

    private fun login(){
        viewModelScope.launch {
            _event.emit(LoginEvent.NavigateToFaceDetection)
        }
    }
}