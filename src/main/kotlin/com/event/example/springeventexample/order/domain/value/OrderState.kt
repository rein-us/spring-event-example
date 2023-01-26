package com.event.example.springeventexample.order.domain.value

enum class OrderState {
    PAYMENT_WAITING {
        override fun isShippingChangeable(): Boolean = true
    },
    PREPARING {
        override fun isShippingChangeable(): Boolean = true
    },
    SHIPPED, DELIVERING, DELIVERY_COMPLETED;
    open fun isShippingChangeable(): Boolean = false
}
