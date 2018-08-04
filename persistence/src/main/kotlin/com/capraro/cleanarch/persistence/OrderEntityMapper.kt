package com.capraro.cleanarch.persistence

import com.capraro.cleanarch.order.model.Order
import com.capraro.cleanarch.order.model.OrderItem

fun OrderEntity.toDomain(): Order {
    return Order(id, customerName, status, items.map { it.toDomain() })
}

fun OrderItemEntity.toDomain(): OrderItem {
    return OrderItem(product, quantity, size, milk)
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(id, customer, status, cost, items.map { it.toEntity() })
}

fun OrderItem.toEntity(): OrderItemEntity {
    return OrderItemEntity(null, product, quantity, size, milk)
}