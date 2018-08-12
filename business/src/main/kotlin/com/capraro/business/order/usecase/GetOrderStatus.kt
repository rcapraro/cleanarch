package com.capraro.business.order.usecase

import com.capraro.business.order.model.OrderStatus

interface GetOrderStatus {
    fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T
}

data class GetOrderStatusRequest(val orderId: String)

data class GetOrderStatusResponse(val orderStatus: OrderStatus)