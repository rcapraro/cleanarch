package com.capraro.cleanarch.usecase.boundary

import com.capraro.cleanarch.model.Milk
import com.capraro.cleanarch.model.Size
import com.capraro.cleanarch.model.Status

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