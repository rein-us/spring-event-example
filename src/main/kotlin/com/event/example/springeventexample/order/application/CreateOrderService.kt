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
        val order = with(request) {
            Order(
                this.userId, 
                this.productId, 
                this.price, 
                this.count,
                this.address.country,
                this.address.state,
                this.address.city,
                this.address.address
            )
        }
        return orderRepository.save(order).id
    }
}
