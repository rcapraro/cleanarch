package com.capraro.cleanarch.infra.web

import com.capraro.cleanarch.usecase.CreateOrder
import com.capraro.cleanarch.usecase.CreateOrderRequest
import com.capraro.cleanarch.usecase.CreateOrderResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RequestMapping("/orders")
@RestController
class OrderController(val createOrder: CreateOrder) {

    @PostMapping(produces = ["application/hal+json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest): CreateOrderResponseBody {
        return createOrder.create(createOrderRequest) {
            it.toResponseBody()
        }
    }
}

data class HalLink(val href: String)

data class CreateOrderResponseBody(val id: String, val customer: String, val amount: BigDecimal, val _links: Map<String, HalLink>)

fun CreateOrderResponse.toResponseBody(): CreateOrderResponseBody {
    val links = mapOf(Pair("status", HalLink("/$id/status")))
    return CreateOrderResponseBody(id, customer, amount, links)
}