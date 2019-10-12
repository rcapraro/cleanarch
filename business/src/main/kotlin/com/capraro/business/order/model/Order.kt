package com.capraro.business.order.model

import java.math.BigDecimal

class Order(val id: String, val customer: String, var status: OrderStatus, val items: List<OrderItem>) {

    var cost: BigDecimal = items.fold(BigDecimal.ZERO) { acc, e -> acc + BigDecimal(e.quantity * (e.size.ordinal + 1) * 5) }

    fun pay(amount: BigDecimal) {
        if (status == OrderStatus.OPEN) {
            if (amount.compareTo(this.cost) == 0) {
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