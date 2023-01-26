package com.event.example.springeventexample.order.domain.entity

import com.event.example.springeventexample.order.domain.entity.LongIdConverter.Companion.NOT_EXIST_ID
import com.event.example.springeventexample.order.domain.entity.OrderingTimeConverter.Companion.NOT_EXIST_ORDERING_TIME
import com.event.example.springeventexample.order.domain.value.OrderState
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Convert(converter = LongIdConverter::class)
    val id: Long,

    @Column(name = "buyer_id")
    @Convert(converter = LongIdConverter::class)
    private val userId: Long,

    @Column(name = "order_state")
    @Enumerated(EnumType.STRING)
    private val state: OrderState = OrderState.PAYMENT_WAITING,
    
    @Column(name = "ordering_time")
    @Convert(converter = OrderingTimeConverter::class)
    private val orderingTime: Long = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli()
) {
    constructor(userId: Long): this(id = NOT_EXIST_ID, userId = userId)
    
    protected constructor(): this(id = NOT_EXIST_ID, userId = NOT_EXIST_ID, orderingTime = NOT_EXIST_ORDERING_TIME)
    
    companion object { 
        val NOT_EXIST = Order() 
    } 
}

fun Order?.exist(): Boolean = this?.let { it.id != NOT_EXIST_ID } ?: false

@Converter(autoApply = false)
class LongIdConverter : AttributeConverter<Long, BigInteger?> {
    override fun convertToDatabaseColumn(id: Long) =
        if (id == NOT_EXIST_ID) null
        else id.toBigInteger()

    override fun convertToEntityAttribute(id: BigInteger?) =
        id?.let { it.toLong() } ?: NOT_EXIST_ID

    companion object {
        const val NOT_EXIST_ID = -1L
    }
}

@Converter(autoApply = false)
class OrderingTimeConverter : AttributeConverter<Long, BigInteger?> {
    override fun convertToDatabaseColumn(orderingTime: Long) =
        if (orderingTime == NOT_EXIST_ORDERING_TIME) null
        else orderingTime.toBigInteger()

    override fun convertToEntityAttribute(orderingTime: BigInteger?) =
        orderingTime?.let { it.toLong() } ?: NOT_EXIST_ORDERING_TIME

    companion object {
        const val NOT_EXIST_ORDERING_TIME = -1L
    }
}
