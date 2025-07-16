package com.akfgfragments

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    val url = environment.config.property("mariadb.url").getString()
    val user = environment.config.property("mariadb.user").getString()
    val password = environment.config.property("mariadb.password").getString()

    Database.connect(
        url,
        driver = "org.mariadb.jdbc.Driver",
        user = user,
        password = password
    )
}