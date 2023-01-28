package com.event.example.springeventexample.order.domain.entity

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

    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private val state: OrderState = OrderState.PAYMENT_WAITING,
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private val address: Address,
    
    @Column(name = "ordering_time")
    @Convert(converter = OrderingTimeConverter::class)
    private val orderingTime: Long = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli()
) {
    constructor(userId: Long): this(id = NOT_EXIST_ID, userId = userId, address = Address.NOT_EXIST_ADDRESS)
    
    protected constructor(): this(id = NOT_EXIST_ID, userId = NOT_EXIST_ID, orderingTime = NOT_EXIST_ORDERING_TIME, address = Address.NOT_EXIST_ADDRESS)
    
    companion object { 
        val NOT_EXIST = Order() 
    } 
}

fun Order?.exist(): Boolean = this?.let { it.id != NOT_EXIST_ID } ?: false
