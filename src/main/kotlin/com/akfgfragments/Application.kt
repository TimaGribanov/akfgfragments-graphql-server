package com.akfgfragments

import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
//    log.info("The app is running on port: {}", port)

    configureDatabases(environment.config)
    configureRouting()
    graphQLModule()
}