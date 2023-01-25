package com.event.example.springeventexample.order.domain.value

import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.Column

data class OrderId(
    @Column(name = "buyer_id")
    val userId: Long,
    @Column(name = "product_id")
    val productId: Long,
    @Column(name = "ordering_time")
    val orderingTime: Long = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli(), 
) : Serializable {
    constructor(): this(-1, -1, -1)
    companion object {
        val NOT_EXIST = OrderId()
    }
}
