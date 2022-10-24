package com.dgsdddsample.presentation

import org.koin.core.context.startKoin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DgsDddSampleApplication

fun main(args: Array<String>) {
    startKoin {
        modules(appModule)
    }
    runApplication<DgsDddSampleApplication>(*args)
}
