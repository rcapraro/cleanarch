package com.capraro.business.order.interactor

import com.capraro.business.UseCase
import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.model.Order
import com.capraro.business.order.model.OrderItem
import com.capraro.business.order.model.OrderStatus
import com.capraro.business.order.usecase.CreateOrder
import com.capraro.business.order.usecase.CreateOrderRequest
import com.capraro.business.order.usecase.CreateOrderRequestItem
import com.capraro.business.order.usecase.CreateOrderResponse
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