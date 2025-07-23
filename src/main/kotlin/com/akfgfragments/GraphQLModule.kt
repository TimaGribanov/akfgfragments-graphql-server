package com.akfgfragments

import com.akfgfragments.schema.LyricsQueryService
import com.akfgfragments.schema.ReleaseQueryService
import com.akfgfragments.schema.SongQueryService
import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.ktor.graphQLSDLRoute
import com.expediagroup.graphql.server.ktor.graphiQLRoute
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlin.time.Duration.Companion.seconds

@Suppress("unused")
fun Application.graphQLModule() {
    install(WebSockets) {
        pingPeriod = 1.seconds
        contentConverter = JacksonWebsocketContentConverter()
    }
    install(CORS) {
        anyHost()
    }
    install(GraphQL) {
        schema {
            packages = listOf("com.akfgfragments")
            queries = listOf(
                ReleaseQueryService(),
                SongQueryService(),
                LyricsQueryService()
            )
//            mutations = listOf(
//                LoginMutationService()
//            )
//            subscriptions = listOf(
//                ExampleSubscriptionService()
//            )
        }
    }
    routing {
        graphQLPostRoute()
//        graphQLSubscriptionsRoute()
        graphiQLRoute()
        graphQLSDLRoute()
    }
}