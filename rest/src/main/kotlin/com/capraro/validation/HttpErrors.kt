package com.capraro.validation

import org.springframework.http.HttpStatus

/**
 * Http Errors enum.
 *
 * These errors appears in the json representation of the error in the field *error*.
 * @see <a href="https://www.iana.org/assignments/http-status-codes">HTTP Status Code Registry</a>
 * @see <a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">List of HTTP status codes - Wikipedia</a>
 * @property code the Http code.
 * @property status the corresponding Http status.
 */
enum class HttpErrors(val code: String, val status: HttpStatus) {
    /**
     * Internal server error (500).
     */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * Unprocessable Entity (422).
     */
    UNPROCESSABLE_ENTITY("UNPROCESSABLE_ENTITY", HttpStatus.UNPROCESSABLE_ENTITY),
    /**
     * Bad Request (400).
     */
    BAD_REQUEST("INVALID_REQUEST", HttpStatus.BAD_REQUEST),
    /**
     * Not Found (404).
     */
    NOT_FOUND("RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND),
    /**
     * Not Authorized (401).
     */
    FORBIDDEN("NOT_AUTHORIZED", HttpStatus.FORBIDDEN),
}