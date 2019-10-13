package com.capraro.validation

import com.capraro.kalidation.spec.ValidationResult

/**
 * Bad Request exception.
 * Produces a **400** http code *(BAD_REQUEST)*
 *
 * @property message the exception message
 * @property details the set of [ValidationResult] containing the details of the exception.
 */
class BadRequestException(message: String?, val details: Set<ValidationResult>?) : RuntimeException(message)