package com.capraro.cleanarch.order.interactor

import com.capraro.cleanarch.UseCase
import com.capraro.cleanarch.order.gateway.OrderGateway
import com.capraro.cleanarch.order.model.Order
import com.capraro.cleanarch.order.model.OrderItem
import com.capraro.cleanarch.order.model.OrderStatus
import com.capraro.cleanarch.order.usecase.CreateOrder
import com.capraro.cleanarch.order.usecase.CreateOrderRequest
import com.capraro.cleanarch.order.usecase.CreateOrderRequestItem
import com.capraro.cleanarch.order.usecase.CreateOrderResponse
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
        return Order(id, customer, OrderStatus.OPEN, items.map { it.toOrderItem() })
    }

    fun CreateOrderRequestItem.toOrderItem(): OrderItem {
        return OrderItem(product, quantity, coffeeSize, milk)
    }

    fun Order.toResponse(): CreateOrderResponse {
        return CreateOrderResponse(id, customer, cost)
    }

}