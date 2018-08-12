package com.capraro.business.order.usecase

import com.capraro.business.order.model.CoffeeSize
import com.capraro.business.order.model.Milk
import com.capraro.business.order.model.OrderStatus

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