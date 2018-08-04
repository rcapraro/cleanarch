package com.capraro.cleanarch.order.usecase

import com.capraro.cleanarch.order.model.CoffeeSize
import com.capraro.cleanarch.order.model.Milk
import java.math.BigDecimal

interface CreateOrder {
    fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T
}

data class CreateOrderRequest(val customer: String, val items: List<CreateOrderRequestItem>)

data class CreateOrderRequestItem(val product: String, val quantity: Int, val coffeeSize: CoffeeSize, val milk: Milk)

data class CreateOrderResponse(val id: String, val customer: String, val amount: BigDecimal)

