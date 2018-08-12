package com.capraro.business.order.interactor

import com.capraro.business.UseCase
import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.usecase.DeleteOrder
import com.capraro.business.order.usecase.DeleteOrderRequest

@UseCase
class DeleteOrderImpl(val orderGateway: OrderGateway) : DeleteOrder {
    override fun delete(request: DeleteOrderRequest) {
        orderGateway.deleteOrder(request.orderId)
    }
}