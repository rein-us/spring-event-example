package com.event.example.springeventexample.support

import com.event.example.springeventexample.SpringEventExampleApplication
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ SpringEventExampleApplication::class ])
@AutoConfigureMockMvc
@TestPropertySource(locations = [ "classpath:application-local.yml" ])
abstract class IntegrationTest {
    @Autowired
    protected lateinit var mvc: MockMvc
}
