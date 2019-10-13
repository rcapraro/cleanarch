package com.capraro.business.order.usecase

import arrow.data.Validated
import com.capraro.business.Request
import com.capraro.business.order.model.CoffeeSize
import com.capraro.business.order.model.Milk
import com.capraro.kalidation.constraints.function.notEmpty
import com.capraro.kalidation.constraints.function.range
import com.capraro.kalidation.constraints.function.size
import com.capraro.kalidation.constraints.function.valid
import com.capraro.kalidation.dsl.constraints
import com.capraro.kalidation.dsl.eachElement
import com.capraro.kalidation.dsl.property
import com.capraro.kalidation.dsl.validationSpec
import com.capraro.kalidation.spec.ValidationResult
import java.math.BigDecimal
import java.util.*

interface CreateOrder {
    fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T
}

data class CreateOrderRequest(val customer: String, val items: MutableList<CreateOrderRequestItem>) : Request {
    override fun validate(locale: Locale): Validated<Set<ValidationResult>, Boolean> {

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


