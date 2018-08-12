package com.capraro.business.order.interactor

import com.capraro.business.UseCase
import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.model.OrderStatus
import com.capraro.business.order.usecase.GetOrders
import com.capraro.business.order.usecase.GetOrdersResponse

@UseCase
class GetOrdersInteractor(val orderGateway: OrderGateway) : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        return presenter(listOf(GetOrdersResponse("test", "John Doe", OrderStatus.OPEN, listOf())))
    }
}