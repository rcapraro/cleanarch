package com.capraro.cleanarch.order.usecase

import com.capraro.cleanarch.order.model.Milk
import com.capraro.cleanarch.order.model.Size
import com.capraro.cleanarch.order.model.Status

interface GetOrders {
    fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T
}

data class GetOrdersResponse(val id: String,
                             val customer: String,
                             val status: Status,
                             val items: List<GetOrdersResponseItem>)

data class GetOrdersResponseItem(val product: String,
                                 val quantity: Int,
                                 val size: Size,
                                 val milk: Milk)