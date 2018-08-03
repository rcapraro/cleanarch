package com.capraro.cleanarch.usecase.interactor

import com.capraro.cleanarch.model.Status
import com.capraro.cleanarch.usecase.boundary.GetOrders
import com.capraro.cleanarch.usecase.boundary.GetOrdersResponse
import com.capraro.cleanarch.usecase.boundary.UseCase

@UseCase
class GetOrdersInteractor : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        return presenter(listOf(GetOrdersResponse("test", "John Doe", Status.PREPARING, listOf())))
    }
}