package com.capraro.business.order.interactor

import com.capraro.business.UseCase
import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.usecase.PayOrder
import com.capraro.business.order.usecase.PayOrderRequest
import com.capraro.business.order.usecase.PayOrderResponse

@UseCase
class PayOrderImpl(val orderGateway: OrderGateway) : PayOrder {
    override fun <T> pay(request: PayOrderRequest, presenter: (PayOrderResponse) -> T): T {
        val order = orderGateway.getOrder(request.orderId)
        order.pay(request.amount)
        orderGateway.updateOrder(order)
        return presenter(PayOrderResponse(order.status))
    }


}