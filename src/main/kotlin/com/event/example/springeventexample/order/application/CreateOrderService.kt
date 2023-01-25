package com.event.example.springeventexample.order.application

import com.event.example.springeventexample.order.domain.Order
import com.event.example.springeventexample.order.domain.OrderId
import com.event.example.springeventexample.order.domain.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateOrderService(
    @Autowired private val orderRepository: OrderRepository
) {
    fun create(request: OrderCreateRequest): OrderId {
        val order = Order(OrderId(userId = request.userId, productId = request.productId))
        return orderRepository.save(order).id
    }
}
