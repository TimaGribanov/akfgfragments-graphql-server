package com.akfgfragments

import io.ktor.server.config.ApplicationConfig
import org.jetbrains.exposed.sql.Database

fun configureDatabases(config: ApplicationConfig) {
    val url = config.property("mariadb.url").getString()
    val user = config.property("mariadb.user").getString()
    val password = config.property("mariadb.password").getString()

    Database.connect(
        url,
        driver = "org.mariadb.jdbc.Driver",
        user = user,
        password = password
    )
}