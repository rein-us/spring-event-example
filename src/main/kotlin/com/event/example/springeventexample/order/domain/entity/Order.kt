package com.event.example.springeventexample.order.domain.entity

import com.event.example.springeventexample.order.domain.entity.converter.AmountConverter
import com.event.example.springeventexample.order.domain.entity.converter.AmountConverter.Companion.ZERO
import com.event.example.springeventexample.order.domain.entity.converter.LongIdConverter
import com.event.example.springeventexample.order.domain.entity.converter.LongIdConverter.Companion.NOT_EXIST_ID
import com.event.example.springeventexample.order.domain.entity.converter.OrderingTimeConverter
import com.event.example.springeventexample.order.domain.entity.converter.OrderingTimeConverter.Companion.NOT_EXIST_ORDERING_TIME
import com.event.example.springeventexample.order.domain.value.OrderState
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Convert(converter = LongIdConverter::class)
    @Column(name = "order_id")
    val id: Long,

    @Column(name = "buyer_id")
    @Convert(converter = LongIdConverter::class)
    private val userId: Long,
    
    @Column(name = "purchased_product_id")
    private val productId: Long,

    @Embedded
    private val amount: Amount,
    
    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private val state: OrderState = OrderState.PAYMENT_WAITING,
    
    @Embedded
    private val address: Address,
    
    @Column(name = "ordering_time")
    @Convert(converter = OrderingTimeConverter::class)
    private val orderingTime: Long = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli()
) {
    constructor(userId: Long, 
                productId: Long, 
                price: Long, 
                count: Long,
                country: String,
                state: String,
                city: String,
                address: String): this(
        id = NOT_EXIST_ID, 
        userId = userId, 
        productId = productId, 
        amount = Amount(price, count),
        address = Address(country, state, city, address))
    
    protected constructor(): this(
        id = NOT_EXIST_ID, 
        userId = NOT_EXIST_ID, 
        orderingTime = NOT_EXIST_ORDERING_TIME, 
        productId = NOT_EXIST_ID,
        amount = Amount.ZERO,
        address = Address.NOT_EXIST_ADDRESS)
    
    companion object { 
        val NOT_EXIST = Order() 
    } 
}

fun Order?.exist(): Boolean = this?.let { it.id != NOT_EXIST_ID } ?: false

@Embeddable
data class Amount(
    @Column(name = "purchased_price")
    @Convert(converter = AmountConverter::class)
    private val price: Long,
    
    @Column(name = "purchased_count")
    @Convert(converter = AmountConverter::class)
    private val count: Long
) {
    protected constructor(): this(AmountConverter.ZERO, AmountConverter.ZERO)
    
    companion object {
        val ZERO = Amount()
    }
}
