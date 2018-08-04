package com.capraro.cleanarch.order.usecase.gateway

import com.capraro.cleanarch.order.model.Order
import com.capraro.cleanarch.order.model.OrderStatus

interface OrderGateway {

    fun createOrder(order: Order)

    fun getOrders(): List<Order>

    fun getOrder(orderId: String): Order

    fun getOrderStatus(orderId: String): OrderStatus

    fun payOrder(orderId: String): OrderStatus
}