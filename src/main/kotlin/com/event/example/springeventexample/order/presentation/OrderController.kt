package com.event.example.springeventexample.order.presentation

import com.event.example.springeventexample.order.application.CreateOrderService
import com.event.example.springeventexample.order.application.OrderCreateRequest
import com.event.example.springeventexample.order.application.SearchOrderService
import com.event.example.springeventexample.order.domain.entity.exist
import com.event.example.springeventexample.order.domain.value.OrderId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController()
@RequestMapping("/order")
class OrderController {
    @Autowired 
    private lateinit var searchOrderService: SearchOrderService
    @Autowired
    private lateinit var createOrderService: CreateOrderService
    
    @GetMapping()
    fun search(): ResponseEntity<Any> {
        val order = searchOrderService.searchBy(OrderId(1L,123L, 321L))
        if (!order.exist()) {
            return ResponseEntity.notFound().build()
        }
        println(">> orderId=${order!!.id}")
        return ResponseEntity.ok().build()
    }
    
    @PostMapping(produces = ["application/json"])
    fun create(@RequestBody request: OrderCreateRequest): ResponseEntity<OrderId> {
        val orderId = createOrderService.create(request)
        return ResponseEntity.ok(orderId)
    }
}
