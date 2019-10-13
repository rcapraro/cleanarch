package com.capraro.validation

import com.capraro.kalidation.spec.ValidationResult
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonPointer
import com.fasterxml.jackson.core.JsonProcessingException
import mu.KotlinLogging
import org.springframework.context.MessageSource
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Custom Controller advice.
 * This Controller advice transforms all exception in a human-readable JSON containing the detail of the fields in error.
 *
 * @property messageSource the [MessageSource] object.
 */
@ControllerAdvice
class CustomControllerAdvice(val messageSource: MessageSource) {
    /**
     * Handles [BadRequestException].
     */
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, locale: Locale): ResponseEntity<CustomAPIError> {
        logger.warn(ex) { "Bad request error" }
        val httpError = HttpErrors.BAD_REQUEST
        val customAPIError = CustomAPIError(Date(),
                httpError.code,
                messageSource.getHttpErrorMessage(httpError.code, locale),
                ex.details?.map { it.toValidationAPIDetail() }?.toSet())
        return ResponseEntity(customAPIError, httpError.status)
    }

    /**
     * Handles [NotFoundException].
     */
    @ExceptionHandler(NotFoundException::class)
    fun handleException(ex: NotFoundException, locale: Locale): ResponseEntity<CustomAPIError> {
        logger.warn(ex) { "Not Found error" }
        val httpError = HttpErrors.NOT_FOUND
        val customAPIError = CustomAPIError(Date(),
                httpError.code,
                messageSource.getHttpErrorMessage(httpError.code, locale),
                setOf(ValidationAPIDetail("email", messageSource.getMessageByKey("error.email.NOT_FOUND", locale))))
        return ResponseEntity(customAPIError, httpError.status)
    }

    /**
     * Handles [HttpMessageNotReadableException].
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleJacksonException(ex: Exception, locale: Locale): ResponseEntity<CustomAPIError> {
        logger.error(ex) { "Serialization error" }
        val httpError = HttpErrors.BAD_REQUEST
        val jsonParseException = (ex.cause as? JsonProcessingException)
        val jsonProcessor = (jsonParseException?.processor as? JsonParser)
        val detail = jsonProcessor?.parsingContext?.pathAsPointer()?.toJsonPath()?.let {
            setOf(ValidationAPIDetail(it, messageSource.getMessageByKey("error.json.INVALID_FORMAT", locale)))
        }
        val customAPIError = CustomAPIError(Date(), httpError.code, messageSource.getHttpErrorMessage(httpError.code, locale), detail)
        return ResponseEntity(customAPIError, httpError.status)
    }

    /**
     * Handles all the other Exceptions.
     */
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, locale: Locale): ResponseEntity<CustomAPIError> {
        logger.error(ex) { "System error" }
        val httpError = HttpErrors.INTERNAL_SERVER_ERROR
        val customAPIError = CustomAPIError(Date(), httpError.code, messageSource.getHttpErrorMessage(httpError.code, locale), null)
        return ResponseEntity(customAPIError, httpError.status)
    }
}

/**
 * Represent an API Error that will be displayed in JSON as a response.
 *
 * @property timestamp the timestamp of the error.
 * @property error the code of the error.
 * @property message the message of the error.
 * @property detail the detail of errors by fields.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class CustomAPIError(val timestamp: Date,
                          val error: String,
                          val message: String?,
                          val detail: Set<ValidationAPIDetail>?)

/**
 * Represent the detail of an error on a field.
 * @property field the field in error.
 * @property message the error message on this field.
 */
data class ValidationAPIDetail(val field: String, val message: String)

/**
 * Map a [ValidationResult] to a [ValidationAPIDetail].
 */
fun ValidationResult.toValidationAPIDetail(): ValidationAPIDetail {
    return ValidationAPIDetail(field = this.fieldName, message = message)
}

/**
 * Get the Http error message for the given key.
 * @param messageKey the message key.
 * @param locale the locale.
 */
fun MessageSource.getHttpErrorMessage(messageKey: String, locale: Locale): String {
    return getMessage("error.http.$messageKey", null, locale)
}

/**
 * Get the error message (not HTTP) for the given key.
 * @param messageKey the message key.
 * @param locale the locale.
 */
fun MessageSource.getMessageByKey(messageKey: String, locale: Locale): String {
    return getMessage(messageKey, null, locale)
}

/**
 * Map a JsonPointer to a String representing the field path in the JSON.
 */
fun JsonPointer.toJsonPath() = toString().removePrefix("/").replace("/", ".")
