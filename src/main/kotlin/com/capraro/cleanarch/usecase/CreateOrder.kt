package com.capraro.cleanarch.usecase

import com.capraro.cleanarch.model.Milk
import com.capraro.cleanarch.model.Size
import java.math.BigDecimal

interface CreateOrder {
    fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T
}

data class CreateOrderRequest(val customer: String, val items: List<CreateOrderRequestItem>)

data class CreateOrderRequestItem(val product: String, val quantity: Int, val size: Size, val milk: Milk)

data class CreateOrderResponse(val id: String, val customer: String, val amount: BigDecimal)

