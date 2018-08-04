package com.capraro.cleanarch.order.usecase.interactor

import com.capraro.cleanarch.order.usecase.PayOrder
import com.capraro.cleanarch.order.usecase.PayOrderRequest
import com.capraro.cleanarch.order.usecase.PayOrderResponse
import com.capraro.cleanarch.order.usecase.UseCase
import com.capraro.cleanarch.order.usecase.gateway.OrderGateway

@UseCase
class PayOrderImpl(val orderGateway: OrderGateway) : PayOrder {
    override fun <T> pay(request: PayOrderRequest, presenter: (PayOrderResponse) -> T): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}