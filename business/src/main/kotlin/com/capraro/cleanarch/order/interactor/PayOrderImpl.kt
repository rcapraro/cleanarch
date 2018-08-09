package com.capraro.cleanarch.order.interactor

import com.capraro.cleanarch.UseCase
import com.capraro.cleanarch.order.gateway.OrderGateway
import com.capraro.cleanarch.order.usecase.PayOrder
import com.capraro.cleanarch.order.usecase.PayOrderRequest
import com.capraro.cleanarch.order.usecase.PayOrderResponse

@UseCase
class PayOrderImpl(val orderGateway: OrderGateway) : PayOrder {
    override fun <T> pay(request: PayOrderRequest, presenter: (PayOrderResponse) -> T): T {
        val order = orderGateway.getOrder(request.orderId)
        order.pay(request.amount)
        orderGateway.updateOrder(order)
        return presenter(PayOrderResponse(order.status))
    }


}