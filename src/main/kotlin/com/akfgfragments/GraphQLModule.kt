package com.akfgfragments//package com.akfgfragments
//
//import com.expediagroup.graphql.server.ktor.GraphQL
//import com.expediagroup.graphql.server.ktor.defaultGraphQLStatusPages
//import com.expediagroup.graphql.server.ktor.graphQLGetRoute
//import com.expediagroup.graphql.server.ktor.graphQLPostRoute
//import com.expediagroup.graphql.server.ktor.graphQLSDLRoute
//import com.expediagroup.graphql.server.ktor.graphQLSubscriptionsRoute
//import com.expediagroup.graphql.server.ktor.graphiQLRoute
//import io.ktor.serialization.jackson.JacksonWebsocketContentConverter
//import io.ktor.server.application.Application
//import io.ktor.server.application.install
//import io.ktor.server.plugins.cors.routing.CORS
//import io.ktor.server.plugins.statuspages.StatusPages
//import io.ktor.server.routing.routing
//import io.ktor.server.websocket.WebSockets
//import io.ktor.server.websocket.pingPeriod
//import kotlin.time.Duration.Companion.seconds
//
//fun Application.graphQLModule() {
////    install(WebSockets) {
////        pingPeriod = 1.seconds
////        contentConverter = JacksonWebsocketContentConverter()
////    }
//    install(StatusPages) {
//        defaultGraphQLStatusPages()
//    }
//    install(CORS) {
//        anyHost()
//    }
//    install(GraphQL) {
//        schema {
//            packages = listOf("com.akfgfragments")
//            queries = listOf(
//                HelloQueryService(),
//                BookQueryService(),
//                CourseQueryService(),
//                UniversityQueryService(),
//            )
//            mutations = listOf(
//                LoginMutationService()
//            )
//            subscriptions = listOf(
//                ExampleSubscriptionService()
//            )
//        }
//        engine {
//            dataLoaderRegistryFactory = KotlinDataLoaderRegistryFactory(
//                UniversityDataLoader, CourseDataLoader, BookDataLoader
//            )
//        }
//        server {
//            contextFactory = CustomGraphQLContextFactory()
//        }
//    }
//    routing {
//        graphQLGetRoute()
//        graphQLPostRoute()
//        graphQLSubscriptionsRoute()
//        graphiQLRoute()
//        graphQLSDLRoute()
//    }
//}