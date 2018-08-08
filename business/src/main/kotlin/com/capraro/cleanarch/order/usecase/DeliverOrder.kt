package com.capraro.cleanarch.order.usecase

import com.capraro.cleanarch.order.model.OrderStatus

interface DeliverOrder {
    fun <T> deliver(request: DeliverOrderRequest, presenter: (PayOrderResponse) -> T): T
}

data class DeliverOrderRequest(val orderId: String)
data class DeliverOrderResponse(val status: OrderStatus)