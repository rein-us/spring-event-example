package com.event.example.springeventexample.order.domain.entity.converter

import java.math.BigInteger
import javax.persistence.AttributeConverter
import javax.persistence.Converter

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

@Converter
class ShippingConverter : AttributeConverter<String, String?> {
    override fun convertToDatabaseColumn(attribute: String?) =
        attribute?.let { it }

    override fun convertToEntityAttribute(dbData: String?) =
        dbData?.let { it } ?: NOT_EXIST_ADDRESS


    companion object {
        const val NOT_EXIST_ADDRESS = ""
    }
}

@Converter
class AmountConverter : AttributeConverter<Long, BigInteger> {
    override fun convertToDatabaseColumn(amount: Long) = amount.toBigInteger()

    override fun convertToEntityAttribute(amount: BigInteger?) = 
        amount?.let { amount.toLong() } ?: ZERO

    companion object {
        const val ZERO = 0L
    }
}
