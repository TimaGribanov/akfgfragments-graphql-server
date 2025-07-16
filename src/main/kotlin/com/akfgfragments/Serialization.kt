package com.akfgfragments

import com.akfgfragments.db.model.Release
import com.akfgfragments.db.model.ReleaseType
import com.akfgfragments.db.model.Repository
import io.ktor.serialization.kotlinx.json.json

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.delete
import io.ktor.server.response.respond
import io.ktor.server.request.receive
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun Application.configureSerialization(repository: Repository) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        route("/releases") {
            get {
                val releases = repository.allReleases()
                call.respond(releases)
            }

            get("/byType/{type}") {
                val typeAsText = call.parameters["type"]
                println(typeAsText)
                if (typeAsText == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                try {
                    val type = ReleaseType.from(typeAsText)
                    val releases = repository.releasesByType(type)

                    if (releases.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }
                    call.respond(releases)
                } catch (ex: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            post {
                try {
                    val release = call.receive<Release>()
                    repository.addRelease(release)
                    call.respond(HttpStatusCode.NoContent)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{releaseId}") {
                val id = call.parameters["releaseId"]?.toInt()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (repository.removeRelease(id)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }

        route("/songs") {
            get {
                val songs = repository.allSongs()
                call.respond(songs)
            }
        }
    }
}