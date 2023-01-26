package com.event.example.springeventexample.order.presentation

import com.event.example.springeventexample.order.application.CreateOrderService
import com.event.example.springeventexample.order.application.OrderCreateRequest
import com.event.example.springeventexample.order.application.SearchOrderService
import com.event.example.springeventexample.order.domain.entity.exist
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
    
    @GetMapping("/{id}")
    fun search(@PathVariable("id") id: Long): ResponseEntity<Any> {
        val order = searchOrderService.searchBy(id)
        if (!order.exist()) {
            return ResponseEntity.notFound().build()
        }
        println(">> orderId=${order!!.id}")
        return ResponseEntity.ok().build()
    }
    
    @PostMapping(produces = ["application/json"])
    fun create(@RequestBody request: OrderCreateRequest): ResponseEntity<Long> {
        val orderId = createOrderService.create(request)
        return ResponseEntity.ok(orderId)
    }
}
