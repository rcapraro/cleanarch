package com.capraro.cleanarch.order.interactor

import com.capraro.cleanarch.UseCase
import com.capraro.cleanarch.order.gateway.OrderGateway
import com.capraro.cleanarch.order.usecase.DeliverOrder
import com.capraro.cleanarch.order.usecase.DeliverOrderRequest
import com.capraro.cleanarch.order.usecase.PayOrderResponse

@UseCase
class DeliverOrderImpl(val orderGateway: OrderGateway) : DeliverOrder {
    override fun <T> deliver(request: DeliverOrderRequest, presenter: (PayOrderResponse) -> T): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}