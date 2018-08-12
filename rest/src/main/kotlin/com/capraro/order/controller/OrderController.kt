package com.capraro.order.controller

import com.capraro.business.order.usecase.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RequestMapping("/orders")
@RestController
class OrderController(val createOrder: CreateOrder,
                      val getOrders: GetOrders,
                      val getOrderStatus: GetOrderStatus,
                      val payOrder: PayOrder,
                      val deleteOrder: DeleteOrder,
                      val deliverOrder: DeliverOrder) {

    @PostMapping(produces = ["application/hal+json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest): CreateOrderResponseBody {
        return createOrder.create(createOrderRequest) {
            it.toResponseBody()
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
    val links = mapOf(Pair("orderStatus", HalLink("/$id/orderStatus")))
    return CreateOrderResponseBody(id, customer, amount, links)
}

data class GetOrdersResponseBody(val id: String, val customer: String, val status: String)

fun GetOrdersResponse.toResponseBody(): GetOrdersResponseBody {
    return GetOrdersResponseBody(id, customer, orderStatus.name.toLowerCase())
}