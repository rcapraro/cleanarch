package com.capraro.cleanarch.order.usecase.interactor

import com.capraro.cleanarch.order.model.Status
import com.capraro.cleanarch.order.usecase.GetOrders
import com.capraro.cleanarch.order.usecase.GetOrdersResponse
import com.capraro.cleanarch.order.usecase.UseCase

@UseCase
class GetOrdersInteractor : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        return presenter(listOf(GetOrdersResponse("test", "John Doe", Status.PREPARING, listOf())))
    }
}