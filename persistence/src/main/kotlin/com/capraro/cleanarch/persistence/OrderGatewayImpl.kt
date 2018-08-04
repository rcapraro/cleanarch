package com.capraro.cleanarch.persistence

import com.capraro.cleanarch.order.model.Order
import com.capraro.cleanarch.order.model.OrderStatus
import com.capraro.cleanarch.order.usecase.gateway.OrderGateway
import org.springframework.stereotype.Component

@Component
class MockOrderGateway : OrderGateway {
    override fun createOrder(order: Order) {
        // TODO save order
    }

    override fun getOrder(orderId: String): Order {
        return Order("o1", "Richard Capraro", OrderStatus.OPEN, listOf())
    }

    override fun getOrders(): List<Order> {
        return listOf(
                Order("o1", "Richard Capraro", OrderStatus.OPEN, listOf()),
                Order("o2", "Richard Capraro", OrderStatus.OPEN, listOf())
        )
    }

    override fun getOrderStatus(orderId: String): OrderStatus {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun payOrder(orderId: String): OrderStatus {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}