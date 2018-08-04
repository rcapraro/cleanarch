package com.capraro.cleanarch.order.usecase

interface DeliverOrder {
    fun <T> deliverOrders()
}