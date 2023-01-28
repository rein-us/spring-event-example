package com.event.example.springeventexample.order.domain.entity

import com.event.example.springeventexample.order.domain.entity.converter.ShippingConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
class Address(
    @Column(name = "address_country")
    @Convert(converter = ShippingConverter::class)
    private val country: String,
    
    @Column(name = "address_state")
    @Convert(converter = ShippingConverter::class)
    private val state: String,
    
    @Column(name = "address_city")
    @Convert(converter = ShippingConverter::class)
    private val city: String,
    
    @Column(name = "address_addr")
    @Convert(converter = ShippingConverter::class)
    private val address: String
) {
    protected constructor(): this(
        ShippingConverter.NOT_EXIST_ADDRESS,
        ShippingConverter.NOT_EXIST_ADDRESS,
        ShippingConverter.NOT_EXIST_ADDRESS,
        ShippingConverter.NOT_EXIST_ADDRESS
    )
    
    companion object {
        val NOT_EXIST_ADDRESS = Address()
    }
}
