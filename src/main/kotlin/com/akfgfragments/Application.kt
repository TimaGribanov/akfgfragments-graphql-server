package com.akfgfragments

import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.application.port

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    log.info("The app is running on port: {}", environment.config.port)

    configureDatabases(environment.config)
    configureRouting()
    graphQLModule()
}