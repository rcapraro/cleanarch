package com.capraro.cleanarch.order.interactor

import com.capraro.cleanarch.UseCase
import com.capraro.cleanarch.order.gateway.OrderGateway
import com.capraro.cleanarch.order.usecase.GetOrderStatus
import com.capraro.cleanarch.order.usecase.GetOrderStatusRequest
import com.capraro.cleanarch.order.usecase.GetOrderStatusResponse

@UseCase
class GetOrderStatusImpl(val orderGateway: OrderGateway) : GetOrderStatus {
    override fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T {
        val orderStatus = orderGateway.getOrderStatus(request.orderId)
        return presenter(GetOrderStatusResponse(orderStatus))
    }
}