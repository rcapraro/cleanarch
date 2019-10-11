package com.capraro.business.order.usecase

import com.capraro.business.order.model.OrderStatus

interface DeliverOrder {
    fun <T> deliver(request: DeliverOrderRequest, presenter: (DeliverOrderResponse) -> T): T
}

data class DeliverOrderRequest(val orderId: String)
data class DeliverOrderResponse(val status: OrderStatus)