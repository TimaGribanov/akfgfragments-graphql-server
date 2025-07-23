description = "akfgfragments.com - back-end custom data GraphQL server"

plugins {
    kotlin("jvm") version "2.1.21"
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

group = "com.akfgfragments"
version = "0.0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.graphql.kotlin)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.websockets)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.statuspages)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.dao)
    implementation(libs.h2)
    implementation(libs.mariadb.client)
//    testImplementation(libs.ktor.server.test.host)
    implementation(libs.logback.classic)
    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}