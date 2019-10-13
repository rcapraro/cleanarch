package com.capraro.validation

/**
 * Not found exception.
 * Produces a **404** http code *(NOT_FOUND)*.
 *
 * @property message the exception message
 */
class NotFoundException(message: String?) : RuntimeException(message)