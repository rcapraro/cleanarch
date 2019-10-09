package com.capraro.persistence

import com.capraro.business.order.model.OrderStatus
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal


@ExtendWith(SpringExtension::class)
@DataJpaTest
class OrderJpaRepositoryTest {

    @Autowired
    lateinit var orderRepository: OrderJpaRepository

    @Test
    fun testOrderRepository() {
        orderRepository.save(OrderEntity("123", "test", OrderStatus.OPEN, BigDecimal.ONE, listOf()))
        assertThat(orderRepository.findById("123").isPresent)
    }
}