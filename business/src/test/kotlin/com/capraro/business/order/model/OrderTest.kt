package com.capraro.business.order.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class OrderTest {

    @Test
    fun `pay an OPEN order with a sufficient amount should succeed`() {

        val order = Order("id1",
                "John Doe",
                OrderStatus.OPEN,
                listOf(OrderItem("Expresso", 2, CoffeeSize.SMALL, Milk.WHOLE)))

        order.pay(BigDecimal.TEN)
    }

    @Test
    fun `pay an OPEN order with a insufficient amount should fail`() {

        val order = Order("id1",
                "John Doe",
                OrderStatus.OPEN,
                listOf(OrderItem("Expresso", 2, CoffeeSize.SMALL, Milk.WHOLE)))

        assertThrows<IllegalArgumentException> {
            order.pay(BigDecimal("5"))
        }
    }

    @Test
    fun `pay a DELIVERED order should fail`() {

        val order = Order("id1",
                "John Doe",
                OrderStatus.DELIVERED,
                listOf(OrderItem("Expresso", 2, CoffeeSize.SMALL, Milk.WHOLE)))

        assertThrows<IllegalStateException> {
            order.pay(BigDecimal.TEN)
        }
    }
}