package com.event.example.springeventexample.order.domain.repository

import com.event.example.springeventexample.order.domain.entity.Order
import org.springframework.data.repository.Repository

// Repository interface 를 상속받아 캡슐화를 지킬 수 있다.
interface OrderRepository : Repository<Order, Long> {
    fun findById(id: Long): Order?
    fun save(order: Order): Order
}
