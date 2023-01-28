package com.event.example.springeventexample.order.presentation.response

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderId(
    @JsonProperty("orderId") val orderId: Long
)
