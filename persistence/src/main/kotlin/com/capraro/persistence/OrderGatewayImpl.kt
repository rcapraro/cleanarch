package com.capraro.persistence

import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.model.Order
import com.capraro.business.order.model.OrderStatus
import org.springframework.stereotype.Component

@Component
class OrderJpaGateway(val orderRespository: OrderJpaRepository) : OrderGateway {

    override fun createOrder(order: Order) {
        orderRespository.save(order.toEntity())
    }

    override fun deleteOrder(orderId: String) {
        orderRespository.deleteById(orderId)
    }

    override fun getOrder(orderId: String): Order {
        return orderRespository.getOne(orderId).toDomain()
    }

    override fun getOrders(): List<Order> {
        return orderRespository.findAll().map { it.toDomain() }
    }

    override fun getOrderStatus(orderId: String): OrderStatus {
        return getOrder(orderId).status
    }

    override fun updateOrder(order: Order) {
        orderRespository.save(order.toEntity())
    }

}