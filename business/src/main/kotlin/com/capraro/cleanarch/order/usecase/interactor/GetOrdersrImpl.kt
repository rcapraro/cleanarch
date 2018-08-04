package com.capraro.cleanarch.order.usecase.interactor

import com.capraro.cleanarch.order.model.OrderStatus
import com.capraro.cleanarch.order.usecase.GetOrders
import com.capraro.cleanarch.order.usecase.GetOrdersResponse
import com.capraro.cleanarch.order.usecase.UseCase
import com.capraro.cleanarch.order.usecase.gateway.OrderGateway

@UseCase
class GetOrdersInteractor(val orderGateway: OrderGateway) : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        return presenter(listOf(GetOrdersResponse("test", "John Doe", OrderStatus.OPEN, listOf())))
    }
}