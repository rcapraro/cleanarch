package com.capraro.persistence

import com.capraro.business.order.model.CoffeeSize
import com.capraro.business.order.model.Milk
import com.capraro.business.order.model.OrderStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal


@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class OrderJpaRepositoryTest {

    @Autowired
    lateinit var orderRepository: OrderJpaRepository

    @Test
    fun testSaveOrder() {
        orderRepository.save(OrderEntity("789z", "test", OrderStatus.OPEN, BigDecimal.ONE,
                mutableListOf(
                        OrderItemEntity(null, "Cappuccino", 2, CoffeeSize.MEDIUM, Milk.SEMI),
                        OrderItemEntity(null, "Expresso", 1, CoffeeSize.SMALL, Milk.WHOLE)
                )))
        assertThat(orderRepository.findById("789z").isPresent)
        assertThat(orderRepository.getOne("789z").items.size).isEqualTo(2)
    }

    @Test
    fun testGetExistingOrderById() {
        val order = orderRepository.getOne("123a")
        assertThat(order.items.size).isEqualTo(1)
    }

    @Test
    fun testGetExistingOrders() {
        val orders = orderRepository.findAll()
        assertThat(orders).hasSize(2)
                .extracting("id", String::class.java)
                .containsExactly("123a", "456c")
    }
}