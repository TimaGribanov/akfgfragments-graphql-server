package com.akfgfragments

import com.akfgfragments.schema.CreditsQueryService
import com.akfgfragments.schema.LinktreeQueryService
import com.akfgfragments.schema.LyricsQueryService
import com.akfgfragments.schema.MusicVideoQueryService
import com.akfgfragments.schema.PersonQueryService
import com.akfgfragments.schema.ReleaseQueryService
import com.akfgfragments.schema.SongQueryService
import com.akfgfragments.schema.TracklistQueryService
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.generator.federation.directives.ContactDirective
import com.expediagroup.graphql.server.Schema
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

@ContactDirective(
    name = "akfgfragments.com",
    url = "https://github.com/TimaGribanov/akfgfragments-graphql-server"
)
@GraphQLDescription("akfgfragments GraphQL schema description")
class AkfgfragmentsSchema : Schema

fun Application.graphQLModule() {
    install(WebSockets) {
        pingPeriod = 1.seconds
        contentConverter = JacksonWebsocketContentConverter()
    }
    install(CORS) {
        anyHost()
        allowHeader("content-type")
    }
    install(GraphQL) {
        schema {
            packages = listOf("com.akfgfragments")
            queries = listOf(
                ReleaseQueryService(),
                SongQueryService(),
                LyricsQueryService(),
                TracklistQueryService(),
                PersonQueryService(),
                MusicVideoQueryService(),
                LinktreeQueryService(),
                CreditsQueryService()
            )
            typeHierarchy = mapOf()
//            mutations = listOf(
//                LoginMutationService()
//            )
//            subscriptions = listOf(
//                ExampleSubscriptionService()
//            )
            schemaObject = AkfgfragmentsSchema()
        }
    }
    routing {
        graphQLPostRoute()
//        graphQLSubscriptionsRoute()
        graphiQLRoute()
        graphQLSDLRoute()
    }
}