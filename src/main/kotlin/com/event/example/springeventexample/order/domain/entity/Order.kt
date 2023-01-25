package com.event.example.springeventexample.order.domain.entity

import com.event.example.springeventexample.order.domain.value.OrderId
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @EmbeddedId
    val id: OrderId
) {
    constructor(): this(OrderId.NOT_EXIST)
    companion object { 
        val NOT_EXIST = Order() 
    } 
}


fun Order?.exist(): Boolean = this?.let { it.id != OrderId.NOT_EXIST } ?: false
