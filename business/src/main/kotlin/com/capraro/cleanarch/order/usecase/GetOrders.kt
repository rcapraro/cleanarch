package com.capraro.cleanarch.order.usecase

import com.capraro.cleanarch.order.model.CoffeeSize
import com.capraro.cleanarch.order.model.Milk
import com.capraro.cleanarch.order.model.OrderStatus

interface GetOrders {
    fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T
}

data class GetOrdersResponse(val id: String,
                             val customer: String,
                             val orderStatus: OrderStatus,
                             val items: List<GetOrdersResponseItem>)

data class GetOrdersResponseItem(val product: String,
                                 val quantity: Int,
                                 val coffeeSize: CoffeeSize,
                                 val milk: Milk)