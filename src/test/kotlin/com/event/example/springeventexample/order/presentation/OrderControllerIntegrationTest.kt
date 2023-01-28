package com.event.example.springeventexample.order.presentation

import com.event.example.springeventexample.SpringEventExampleApplication
import com.event.example.springeventexample.order.application.AddressRequest
import com.event.example.springeventexample.order.application.OrderCreateRequest
import com.event.example.springeventexample.order.domain.repository.OrderRepository
import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@DisplayName("주문 api 통합 테스트(개별조회)")
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ SpringEventExampleApplication::class ])
@AutoConfigureMockMvc
@TestPropertySource(locations = [ "classpath:application-dev.yml" ])
class OrderControllerIntegrationTest {
    @Autowired
    private lateinit var mvc: MockMvc
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @DisplayName("주문을 조회할 수 있다.")
    @Test
    fun search() {
        mvc.perform(get("/order/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }

    @DisplayName("주문을 등록한다.")
    @Test
    fun create() {
        val request = Gson().toJson(OrderCreateRequest(
            123L, 456L, 1000L, 23L, 
            AddressRequest("Korea", "GyungGido", "Hanam", "address1")
        ))
        mvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
            .content(request))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.orderId").value(1))
    }
}
