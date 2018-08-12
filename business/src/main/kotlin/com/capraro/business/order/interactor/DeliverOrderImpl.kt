package com.capraro.business.order.interactor

import com.capraro.business.UseCase
import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.usecase.DeliverOrder
import com.capraro.business.order.usecase.DeliverOrderRequest
import com.capraro.business.order.usecase.PayOrderResponse

@UseCase
class DeliverOrderImpl(val orderGateway: OrderGateway) : DeliverOrder {
    override fun <T> deliver(request: DeliverOrderRequest, presenter: (PayOrderResponse) -> T): T {
        val order = orderGateway.getOrder(request.orderId)
        order.deliver()
        orderGateway.updateOrder(order)
        return presenter(PayOrderResponse(order.status))
    }
}