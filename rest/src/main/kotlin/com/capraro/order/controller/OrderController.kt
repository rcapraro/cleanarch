package com.capraro.order.controller

import com.capraro.business.order.usecase.*
import com.capraro.validation.RequestHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.net.URI
import java.util.*

@RequestMapping("/orders")
@RestController
class OrderController(private val requestHandler: RequestHandler,
                      private val createOrder: CreateOrder,
                      private val getOrders: GetOrders,
                      private val getOrderStatus: GetOrderStatus,
                      private val payOrder: PayOrder,
                      private val deleteOrder: DeleteOrder,
                      private val deliverOrder: DeliverOrder) {

    @PostMapping(produces = ["application/hal+json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest,
                    locale: Locale): ResponseEntity<CreateOrderResponseBody> {
        return requestHandler.withValidRequest(createOrderRequest, locale)
        { request ->
            createOrder.create(request) {
                ResponseEntity.created(URI("/orders/${it.id}")).body(it.toResponseBody())
            }
        }
    }

    @GetMapping
    fun getOrders(): List<GetOrdersResponseBody> {
        return getOrders.getOrders { order ->
            order.map { it.toResponseBody() }
        }
    }

    @GetMapping("/{orderId}/orderStatus")
    fun getOrderStatus(@PathVariable orderId: String): String {
        return getOrderStatus.getStatus(GetOrderStatusRequest(orderId)) { it.orderStatus.name.toLowerCase() }
    }

    @PostMapping("/{orderId}/payment")
    fun payForOrder(@PathVariable orderId: String, @RequestParam amount: BigDecimal) {
        payOrder.pay(PayOrderRequest(orderId, amount)) {}
    }

    @DeleteMapping("/{orderId}")
    fun deleteOrder(@PathVariable orderId: String) {
        deleteOrder.delete(DeleteOrderRequest(orderId))
    }

    @PostMapping("/{orderId}/delivery")
    fun deliverOrder(@PathVariable orderId: String) {
        deliverOrder.deliver(DeliverOrderRequest(orderId)) {}
    }
}

data class HalLink(val href: String)

data class CreateOrderResponseBody(val id: String, val customer: String, val amount: BigDecimal, val links: Map<String, HalLink>)

fun CreateOrderResponse.toResponseBody(): CreateOrderResponseBody {
    val links = mapOf(Pair("orderStatus", HalLink("/orders/$id/orderStatus")))
    return CreateOrderResponseBody(id, customer, amount, links)
}

data class GetOrdersResponseBody(val id: String, val customer: String, val status: String)

fun GetOrdersResponse.toResponseBody(): GetOrdersResponseBody {
    return GetOrdersResponseBody(id, customer, orderStatus.name.toLowerCase())
}