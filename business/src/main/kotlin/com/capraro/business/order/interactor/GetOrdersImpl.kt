package com.capraro.business.order.interactor

import com.capraro.business.UseCase
import com.capraro.business.order.gateway.OrderGateway
import com.capraro.business.order.model.Order
import com.capraro.business.order.usecase.GetOrders
import com.capraro.business.order.usecase.GetOrdersResponse

@UseCase
class GetOrdersImpl(val orderGateway: OrderGateway) : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        return presenter(orderGateway.getOrders().map { it.toResponse() })
    }
}

fun Order.toResponse(): GetOrdersResponse {
    return GetOrdersResponse(id, customer, orderStatus = this.status, items = emptyList())
}