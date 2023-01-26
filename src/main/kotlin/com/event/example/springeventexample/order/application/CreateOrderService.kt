package com.event.example.springeventexample.order.application

import com.event.example.springeventexample.order.domain.entity.Order
import com.event.example.springeventexample.order.domain.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateOrderService(
    @Autowired private val orderRepository: OrderRepository
) {
    fun create(request: OrderCreateRequest): Long {
        val order = Order(userId = request.userId)
        return orderRepository.save(order).id
    }
}
