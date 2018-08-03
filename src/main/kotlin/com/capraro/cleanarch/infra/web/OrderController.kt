package com.capraro.cleanarch.infra.web

import com.capraro.cleanarch.order.usecase.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RequestMapping("/orders")
@RestController
class OrderController(val createOrder: CreateOrder,
                      val getOrders: GetOrders) {

    @PostMapping(produces = ["application/hal+json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest): CreateOrderResponseBody {
        return createOrder.create(createOrderRequest) {
            it.toResponseBody()
        }
    }

    @GetMapping
    fun getOrders(): List<GetOrdersResponseBody> {
        return getOrders.getOrders {
            it.map { it.toResponseBody() }
        }
    }
}

data class HalLink(val href: String)

data class CreateOrderResponseBody(val id: String, val customer: String, val amount: BigDecimal, val links: Map<String, HalLink>)

fun CreateOrderResponse.toResponseBody(): CreateOrderResponseBody {
    val links = mapOf(Pair("status", HalLink("/$id/status")))
    return CreateOrderResponseBody(id, customer, amount, links)
}

data class GetOrdersResponseBody(val id: String, val customer: String, val status: String)

fun GetOrdersResponse.toResponseBody(): GetOrdersResponseBody {
    return GetOrdersResponseBody(id, customer, status.name.toLowerCase())
}