package com.capraro.cleanarch.usecase.impl

import com.capraro.cleanarch.usecase.CreateOrder
import com.capraro.cleanarch.usecase.CreateOrderRequest
import com.capraro.cleanarch.usecase.CreateOrderResponse
import com.capraro.cleanarch.usecase.UseCase
import java.math.BigDecimal

@UseCase
class MockCreateOrder : CreateOrder {
    override fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T {
        return presenter(CreateOrderResponse("test", request.customer, BigDecimal.TEN))
    }
}