package com.capraro.cleanarch.usecase.interactor

import com.capraro.cleanarch.usecase.boundary.CreateOrder
import com.capraro.cleanarch.usecase.boundary.CreateOrderRequest
import com.capraro.cleanarch.usecase.boundary.CreateOrderResponse
import com.capraro.cleanarch.usecase.boundary.UseCase
import java.math.BigDecimal

@UseCase
class CreateOrderInteractor : CreateOrder {
    override fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T {
        return presenter(CreateOrderResponse("test", request.customer, BigDecimal.TEN))
    }
}