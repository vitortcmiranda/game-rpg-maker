package com.gamerpgmaker.gamerpgmakerwebflux

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition
class GamerpgmakerWebfluxApplication

fun main(args: Array<String>) {
	runApplication<GamerpgmakerWebfluxApplication>(*args)
}
