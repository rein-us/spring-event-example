package com.event.example.springeventexample.order.presentation

import com.event.example.springeventexample.order.application.AddressRequest
import com.event.example.springeventexample.order.application.OrderCreateRequest
import com.event.example.springeventexample.support.IntegrationTest
import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@DisplayName("주문 api 통합 테스트(개별조회)")
class OrderControllerIntegrationTest : IntegrationTest() {
    @Autowired
    private lateinit var controller: OrderController

    @DisplayName("주문을 조회할 수 있다.")
    @Test
    fun search() {
        val orderId = createOrder()
        mvc.perform(get("$BASE_URL/$orderId").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.orderId").value(orderId))
    }

    @DisplayName("주문을 등록한다.")
    @Test
    fun create() {
        val request = Gson().toJson(createOrderRequest)
        mvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.orderId").value(1))
    }
    
    private fun createOrder(): Long = controller.create(createOrderRequest).body!!.orderId
    
    companion object {
        private const val BASE_URL = "/order"
        private val createOrderRequest = OrderCreateRequest(
            123L, 456L, 1000L, 23L,
            AddressRequest("Korea", "GyungGido", "Hanam", "address1")
        )
    }
}
