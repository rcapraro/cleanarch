package com.capraro.cleanarch.order.model

import java.math.BigDecimal
import java.util.*

class Order(val id: String, val customer: String, status: Status, val items: List<OrderItem>) {
    lateinit var cost: BigDecimal
    var status: Status = status
        private set

    fun calculateCost() {
        cost = BigDecimal(Random().nextInt(20))
    }

    fun create() {
        calculateCost()
    }

    fun delete() {

    }
}

data class OrderItem(val product: String, val quantity: Int, val size: Size, val milk: Milk)