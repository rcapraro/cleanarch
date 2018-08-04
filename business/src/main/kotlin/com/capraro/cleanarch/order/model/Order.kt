package com.capraro.cleanarch.order.model

import java.math.BigDecimal
import java.util.*

class Order(val id: String, val customer: String, var status: OrderStatus, val items: List<OrderItem>) {

    var cost: BigDecimal = BigDecimal(Random().nextInt(5)) * items.size.toBigDecimal()

    fun pay(amount: BigDecimal) {
        if (status == OrderStatus.OPEN) {
            if (amount == this.cost) {
                status = OrderStatus.PAID
            } else {
                throw IllegalArgumentException("The paid amount is insufficient")
            }
        } else {
            throw IllegalStateException("Order should be open in order to be paid")
        }
    }

    fun deliver() {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.DELIVERED
        } else {
            throw IllegalStateException("Order has not been paid yet")
        }
    }
}

data class OrderItem(val product: String, val quantity: Int, val size: CoffeeSize, val milk: Milk)