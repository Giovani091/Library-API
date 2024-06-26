package com.library.api2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class LibraryApi2Application

fun main(args: Array<String>) {
	runApplication<LibraryApi2Application>(*args)
}
