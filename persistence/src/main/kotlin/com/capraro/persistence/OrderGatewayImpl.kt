package com.capraro.persistence

import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.model.Order
import com.capraro.business.order.model.OrderStatus
import org.springframework.stereotype.Component

@Component
class OrderJpaGateway(val orderRepository: OrderJpaRepository) : OrderGateway {

    override fun createOrder(order: Order) {
        orderRepository.save(order.toEntity())
    }

    override fun deleteOrder(orderId: String) {
        orderRepository.deleteById(orderId)
    }

    override fun getOrder(orderId: String): Order {
        return orderRepository.getOne(orderId).toDomain()
    }

    override fun getOrders(): List<Order> {
        return orderRepository.findAll().map { it.toDomain() }
    }

    override fun getOrderStatus(orderId: String): OrderStatus {
        return getOrder(orderId).status
    }

    override fun updateOrder(order: Order) {
        orderRepository.save(order.toEntity())
    }

}