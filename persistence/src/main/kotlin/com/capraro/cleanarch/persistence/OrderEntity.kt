package com.capraro.cleanarch.persistence

import com.capraro.cleanarch.order.model.CoffeeSize
import com.capraro.cleanarch.order.model.Milk
import com.capraro.cleanarch.order.model.OrderStatus
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import javax.persistence.*

@Entity
data class OrderEntity(@Id val id: String,
                       val customerName: String,
                       @Enumerated var status: OrderStatus,
                       val cost: BigDecimal,
                       @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
                       @JoinColumn(name = "order_id")
                       val items: List<OrderItemEntity>)

@Entity
data class OrderItemEntity(@GeneratedValue(generator = "UUID")
                           @GenericGenerator(
                                   name = "UUID",
                                   strategy = "org.hibernate.id.UUIDGenerator")
                           @Id val id: String?,
                           val product: String,
                           val quantity: Int,
                           @Enumerated val size: CoffeeSize,
                           @Enumerated val milk: Milk)