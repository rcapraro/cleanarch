package com.capraro.business.order.gateway

import com.capraro.business.order.model.Order
import com.capraro.business.order.model.OrderStatus

interface OrderGateway {

    fun createOrder(order: Order)

    fun getOrders(): List<Order>

    fun getOrder(orderId: String): Order

    fun getOrderStatus(orderId: String): OrderStatus

    fun updateOrder(order: Order)

    fun deleteOrder(orderId: String)
}