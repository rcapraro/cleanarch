package com.capraro.business.order.interactor

import com.capraro.business.UseCase
import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.usecase.GetOrderStatus
import com.capraro.business.order.usecase.GetOrderStatusRequest
import com.capraro.business.order.usecase.GetOrderStatusResponse

@UseCase
class GetOrderStatusImpl(val orderGateway: OrderGateway) : GetOrderStatus {
    override fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T {
        val orderStatus = orderGateway.getOrderStatus(request.orderId)
        return presenter(GetOrderStatusResponse(orderStatus))
    }
}