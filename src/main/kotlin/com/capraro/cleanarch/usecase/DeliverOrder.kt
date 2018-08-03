package com.capraro.cleanarch.usecase

import com.capraro.cleanarch.model.Milk
import com.capraro.cleanarch.model.Size
import com.capraro.cleanarch.model.Status

interface DeliverOrder {
    fun <T> deliverOrders()
}