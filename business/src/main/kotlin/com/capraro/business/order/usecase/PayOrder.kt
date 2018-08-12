package com.capraro.business.order.usecase

import com.capraro.business.order.model.OrderStatus
import java.math.BigDecimal

interface PayOrder {
    fun <T> pay(request: PayOrderRequest, presenter: (PayOrderResponse) -> T): T
}

data class PayOrderRequest(val orderId: String, val amount: BigDecimal)
data class PayOrderResponse(val status: OrderStatus)
