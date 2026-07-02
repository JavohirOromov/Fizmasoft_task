package com.javohir.fizmasofttask.domain.usecase

import javax.inject.Inject

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.domain.usecase
 * Description: Passport validatsiyasi (2 harf + 7 raqam, seriya)
 */
class ValidatePassportUseCase @Inject constructor() {

    operator fun invoke(passport: String): Boolean {
        if (passport.length != 9) return false
        if (!passport.matches(Regex("^[A-Z]{2}\\d{7}$"))) return false
        return passport.take(2) in VALID_SERIES
    }

    companion object {
        private val VALID_SERIES = listOf(
            "AA", "AB", "AC", "AD", "AE", "AF", "AG", "AH", "AK", "AL",
            "AM", "AN", "AO", "AP", "AQ", "AR", "AS", "AT", "AU", "AV",
            "AW", "AX", "AY", "AZ", "BA", "BB", "BC", "BD", "BE", "BF",
            "BG", "BH", "BI", "BK", "BL", "BM", "BN", "BO", "BP", "BQ",
            "BR", "BS", "BT", "BU", "BV", "BW", "BX", "BY", "BZ",
        )
    }
}