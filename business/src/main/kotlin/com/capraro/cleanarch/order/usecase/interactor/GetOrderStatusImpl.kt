package com.capraro.cleanarch.order.usecase.interactor

import com.capraro.cleanarch.order.usecase.GetOrderStatus
import com.capraro.cleanarch.order.usecase.GetOrderStatusRequest
import com.capraro.cleanarch.order.usecase.GetOrderStatusResponse
import com.capraro.cleanarch.order.usecase.UseCase
import com.capraro.cleanarch.order.usecase.gateway.OrderGateway

@UseCase
class GetOrderStatusImpl(val orderGateway: OrderGateway) : GetOrderStatus {
    override fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T {
        val orderStatus = orderGateway.getOrderStatus(request.orderId)
        return presenter(GetOrderStatusResponse(orderStatus))
    }
}