package com.capraro.cleanarch.order.usecase

interface DeleteOrder {
    fun delete(request: DeleteOrderRequest)
}

data class DeleteOrderRequest(val orderId: String)
