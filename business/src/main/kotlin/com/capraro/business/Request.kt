package com.capraro.business

import io.github.rcapraro.kalidation.spec.ValidationResult
import java.util.*
import arrow.core.Validated
import com.capraro.business.order.usecase.CreateOrderRequest

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
    fun validate(locale: Locale): Validated<Set<ValidationResult>, CreateOrderRequest>
}