package com.akfgfragments

import com.akfgfragments.db.model.MariaDbRepository
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    val repository = MariaDbRepository()

    configureSerialization(repository)
    configureDatabases()
    configureRouting()
}