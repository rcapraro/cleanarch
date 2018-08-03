package com.capraro.cleanarch.infra.persistence

import com.capraro.cleanarch.order.model.Order
import com.capraro.cleanarch.order.model.Status
import com.capraro.cleanarch.order.usecase.gateway.OrderGateway
import org.springframework.stereotype.Component

@Component
class MockOrderGateway : OrderGateway {
    override fun createOrder(order: Order) {
        // TODO save order
    }

    override fun getOrder(orderId: String): Order {
        return Order("o1", "Richard Capraro", Status.PREPARING, listOf())
    }

    override fun getOrders(): List<Order> {
        return listOf(
                Order("o1", "Richard Capraro", Status.PREPARING, listOf()),
                Order("o2", "Richard Capraro", Status.PREPARING, listOf())
        )
    }
}