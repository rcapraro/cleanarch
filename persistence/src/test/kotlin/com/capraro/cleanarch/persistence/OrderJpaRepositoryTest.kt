package com.capraro.cleanarch.persistence

import com.capraro.cleanarch.order.model.OrderStatus
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal


@RunWith(SpringRunner::class)
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