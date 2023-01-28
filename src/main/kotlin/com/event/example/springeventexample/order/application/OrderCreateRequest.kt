package com.event.example.springeventexample.order.application

import com.fasterxml.jackson.annotation.JsonProperty

class OrderCreateRequest(
    @JsonProperty("buyerId") val userId: Long,
    @JsonProperty("productId") val productId: Long,
    @JsonProperty("price") val price: Long,
    @JsonProperty("count") val count: Long,
    @JsonProperty("address") val address: AddressRequest
)

data class AddressRequest(
    @JsonProperty("country") val country: String,
    @JsonProperty("state") val state: String,
    @JsonProperty("city") val city: String,
    @JsonProperty("address") val address: String
)
