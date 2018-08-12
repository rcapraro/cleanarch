package com.capraro.business.order.usecase

interface DeleteOrder {
    fun delete(request: DeleteOrderRequest)
}

data class DeleteOrderRequest(val orderId: String)
