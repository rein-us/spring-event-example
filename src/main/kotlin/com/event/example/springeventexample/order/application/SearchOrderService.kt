package com.event.example.springeventexample.order.application

import com.event.example.springeventexample.order.domain.Order
import com.event.example.springeventexample.order.domain.OrderId
import com.event.example.springeventexample.order.domain.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SearchOrderService(
    @Autowired private val orderRepository: OrderRepository
) {
    fun searchBy(id: OrderId): Order =
        orderRepository.findById(id) ?: Order.NOT_EXIST
}
