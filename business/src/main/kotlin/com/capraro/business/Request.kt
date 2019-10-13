package com.capraro.business

import arrow.data.Validated
import com.capraro.kalidation.spec.ValidationResult
import java.util.*

/**
 * Interface for all UseCase requests.
 *
 */
interface Request {
    /**
     * Validation of the request with locale to create i18n error message.
     *
     * @param locale locale.
     * @return result of validation of the request.
     */
    fun validate(locale: Locale): Validated<Set<ValidationResult>, Boolean>
}