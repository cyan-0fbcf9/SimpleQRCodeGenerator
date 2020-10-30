package com.cyan0fbcf9.simpleqrgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleQrGeneratorApplication

fun main(args: Array<String>) {
    runApplication<SimpleQrGeneratorApplication>(*args)
}
