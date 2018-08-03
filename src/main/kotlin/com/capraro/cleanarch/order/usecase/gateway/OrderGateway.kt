package com.capraro.cleanarch.order.usecase.gateway

import com.capraro.cleanarch.order.model.Order

interface OrderGateway {

    fun createOrder(order: Order)

    fun getOrders(): List<Order>

    fun getOrder(orderId: String): Order
}