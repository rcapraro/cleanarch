package com.capraro.validation

import com.capraro.business.Request
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

/**
 * RequestHandler used to validate a Request.
 *
 * If the Request is invalid, a [BadRequestException] is thrown.
 */
@Component
class RequestHandler {

    /**
     * Validate a request submitted to a UseCase.
     *
     * @param R the type of request of the UseCase.
     * @param T the type of response of the UseCase.
     * @param request the request to validate.
     * @param block the code block to invoke if the request is valid.
     * @return a response entity wrapping a response of type T.
     */
    fun <R : Request, T> withValidRequest(request: R, locale: Locale, block: (R) -> ResponseEntity<T>): ResponseEntity<T> {

        val validations = request.validate(locale)

        validations.fold<Nothing>(
                { throw BadRequestException(message = "The request is invalid", details = it) },
                { return block.invoke(request) }
        )
    }
}