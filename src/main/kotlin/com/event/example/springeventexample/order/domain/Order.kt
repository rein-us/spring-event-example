package com.event.example.springeventexample.order.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @EmbeddedId
    val id: OrderId
) {
   companion object {
       val NOT_EXIST = Order(OrderId.NOT_EXIST)
   } 
}

data class OrderId(
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long? = null,
    @Column(name = "buyer_id")
    val userId: Long,
    @Column(name = "product_id")
    val productId: Long
) : Serializable {
    companion object {
        val NOT_EXIST = OrderId(userId = -1, productId = -1)
    }
}

fun Order?.exist(): Boolean = this?.let { it.id != null } ?: false
