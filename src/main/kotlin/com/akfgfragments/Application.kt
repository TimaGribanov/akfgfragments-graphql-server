package com.akfgfragments

import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.cio.CIO
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.engine.embeddedServer

val config = ApplicationConfig("application.yaml")
val port : Int = config.property("ktor.deployment.port").getString().toInt()

fun main() {
    embeddedServer(CIO, port = port, module = Application::module).start(wait = true)
}

@Suppress("unused")
fun Application.module() {
    log.info("The app is running on port: {}", port)

    configureDatabases()
    configureRouting()
    graphQLModule()
}