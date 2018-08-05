package com.capraro.cleanarch.order.interactor

import com.capraro.cleanarch.UseCase
import com.capraro.cleanarch.order.gateway.OrderGateway
import com.capraro.cleanarch.order.model.OrderStatus
import com.capraro.cleanarch.order.usecase.GetOrders
import com.capraro.cleanarch.order.usecase.GetOrdersResponse

@UseCase
class GetOrdersInteractor(val orderGateway: OrderGateway) : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        return presenter(listOf(GetOrdersResponse("test", "John Doe", OrderStatus.OPEN, listOf())))
    }
}