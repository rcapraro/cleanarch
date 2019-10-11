package com.capraro.business.order.usecase

import com.capraro.business.order.model.CoffeeSize
import com.capraro.business.order.model.Milk
import java.math.BigDecimal

interface CreateOrder {
    fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T
}

data class CreateOrderRequest(val customer: String, val items: List<CreateOrderRequestItem>)

data class CreateOrderRequestItem(val product: String, val quantity: Int, val coffeeSize: CoffeeSize, val milk: Milk)

data class CreateOrderResponse(val id: String, val customer: String, val amount: BigDecimal, val items: List<CreateOrderResponseItem>)

data class CreateOrderResponseItem(val product: String, val quantity: Int, val size: CoffeeSize, val milk: Milk)


