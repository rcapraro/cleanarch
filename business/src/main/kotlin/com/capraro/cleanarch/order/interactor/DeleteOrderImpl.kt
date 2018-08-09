package com.capraro.cleanarch.order.interactor

import com.capraro.cleanarch.UseCase
import com.capraro.cleanarch.order.gateway.OrderGateway
import com.capraro.cleanarch.order.usecase.DeleteOrder
import com.capraro.cleanarch.order.usecase.DeleteOrderRequest

@UseCase
class DeleteOrderImpl(val orderGateway: OrderGateway) : DeleteOrder {
    override fun delete(request: DeleteOrderRequest) {
        orderGateway.deleteOrder(request.orderId)
    }
}