package com.capraro.persistence

import com.capraro.business.order.model.Order
import com.capraro.business.order.model.OrderItem

fun OrderEntity.toDomain(): Order {
    return Order(id, customerName, status, items.map { it.toDomain() })
}

fun OrderItemEntity.toDomain(): OrderItem {
    return OrderItem(product, quantity, size, milk)
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(id, customer, status, cost, items.map { it.toEntity() }.toMutableList())
}

fun OrderItem.toEntity(): OrderItemEntity {
    return OrderItemEntity(null, product, quantity, size, milk)
}