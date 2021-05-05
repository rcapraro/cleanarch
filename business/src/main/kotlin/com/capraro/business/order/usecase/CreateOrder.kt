package com.capraro.business.order.usecase

import arrow.core.Validated
import arrow.core.valid
import com.capraro.business.Request
import com.capraro.business.order.model.CoffeeSize
import com.capraro.business.order.model.Milk
import io.github.rcapraro.kalidation.constraints.function.notEmpty
import io.github.rcapraro.kalidation.constraints.function.range
import io.github.rcapraro.kalidation.constraints.function.size
import io.github.rcapraro.kalidation.dsl.constraints
import io.github.rcapraro.kalidation.dsl.eachElement
import io.github.rcapraro.kalidation.dsl.property
import io.github.rcapraro.kalidation.dsl.validationSpec
import io.github.rcapraro.kalidation.spec.ValidationResult
import java.math.BigDecimal
import java.util.*

interface CreateOrder {
    fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T
}

data class CreateOrderRequest(val customer: String, val items: MutableList<CreateOrderRequestItem>) : Request {
    override fun validate(locale: Locale): Validated<Set<ValidationResult>, CreateOrderRequest> {

        val spec = validationSpec(locale, "messages") {

            constraints<CreateOrderRequest> {
                property(CreateOrderRequest::customer) {
                    notEmpty()
                    size(5, 255)
                }
                property(CreateOrderRequest::items) {
                    notEmpty()
                    eachElement {
                        valid()
                    }
                }
            }

            constraints<CreateOrderRequestItem> {
                property(CreateOrderRequestItem::product) {
                    notEmpty()
                    size(5, 255)
                }
                property(CreateOrderRequestItem::quantity) {
                    range(1, 100)
                }
            }
        }

        return spec.validate(this)
    }

}

data class CreateOrderRequestItem(val product: String, val quantity: Int, val coffeeSize: CoffeeSize, val milk: Milk)

data class CreateOrderResponse(val id: String, val customer: String, val amount: BigDecimal, val items: List<CreateOrderResponseItem>)

data class CreateOrderResponseItem(val product: String, val quantity: Int, val size: CoffeeSize, val milk: Milk)


