package com.capraro.cleanarch.order.usecase.interactor

import com.capraro.cleanarch.order.model.Order
import com.capraro.cleanarch.order.model.OrderItem
import com.capraro.cleanarch.order.model.Status
import com.capraro.cleanarch.order.usecase.*
import com.capraro.cleanarch.order.usecase.gateway.OrderGateway
import java.util.*

@UseCase
class CreateOrderInteractor(val orderGateway: OrderGateway) : CreateOrder {
    override fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T {
        val order = request.toOrder()
        orderGateway.createOrder(order)
        return presenter(order.toResponse())
    }

    fun CreateOrderRequest.toOrder(): Order {
        val id = UUID.randomUUID().toString()
        return Order(id, customer, Status.PREPARING, items.map { it.toOrderItem() })
    }

    fun CreateOrderRequestItem.toOrderItem(): OrderItem {
        return OrderItem(product, quantity, size, milk)
    }

    fun Order.toResponse(): CreateOrderResponse {
        return CreateOrderResponse(id, customer, cost)
    }

}