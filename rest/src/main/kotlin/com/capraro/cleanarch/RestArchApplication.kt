package com.capraro.cleanarch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestArchApplication

fun main(args: Array<String>) {
    runApplication<RestArchApplication>(*args)
}
