package com.event.example.springeventexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringEventExampleApplication {
}

fun main(args: Array<String>) {
    runApplication<SpringEventExampleApplication>(*args)
    val profile = System.getProperty("spring.profiles.active")
    println(">> profile=$profile")
    
    System.setProperty("spring.devtools.restart.enabled", "true")
}
