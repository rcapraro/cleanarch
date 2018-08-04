package com.capraro.cleanarch.order.usecase

import com.capraro.cleanarch.order.model.OrderStatus

interface GetOrderStatus {
    fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T
}

data class GetOrderStatusRequest(val orderId: String)

data class GetOrderStatusResponse(val orderStatus: OrderStatus)