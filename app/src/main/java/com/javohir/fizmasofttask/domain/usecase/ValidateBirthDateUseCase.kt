package com.javohir.fizmasofttask.domain.usecase

import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.usecase
 * Description: Tug'ilgan sana validatsiyasi (DDMMYYYY)
 */
class ValidateBirthDateUseCase @Inject constructor() {

    operator fun invoke(date: String): Boolean {
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
}