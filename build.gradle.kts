description = "akfgfragments.com - back-end custom data GraphQL server"

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.graalvm.native)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.graphql.kotlin)
}

group = "com.akfgfragments"
version = "0.1.9"

application {
    mainClass.set("com.akfgfragments.ApplicationKt")
}

kotlin {
    jvmToolchain(25)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.graphql.kotlin)

    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.websockets)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.statuspages)

    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.java.time)
    implementation(libs.h2)
    implementation(libs.mariadb.client)
//    testImplementation(libs.ktor.server.test.host)
    implementation(libs.logback.classic)
//    testImplementation(kotlin("test"))
//    implementation(kotlin("stdlib-jdk8"))
}

ktor {
    fatJar {
        archiveFileName.set("akfgfragments-data-graphql-server-fatJar-${version}.jar")
    }
}

graalvmNative {
    toolchainDetection.set(false)

    agent {
        enabled.set(true)
        metadataCopy {
            inputTaskNames.add("run")
            outputDirectories.add("src/main/resources/META-INF/native-image/org.akfgfragments")
            mergeWithExisting.set(true)
        }
    }

    binaries {
        named("main") {
            verbose.set(true)

            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin")
            buildArgs.add("--initialize-at-build-time=ch.qos.logback")
            buildArgs.add("--initialize-at-build-time=org.slf4j")
            buildArgs.add("--initialize-at-build-time=kotlinx")
            buildArgs.add("--initialize-at-build-time=org.xml.sax.helpers")

            imageName.set("akfgfragments-data-graphql-server-${version}")
        }

        metadataRepository {
            enabled.set(true)
        }
    }
}

graphql {
    graalVm {
        packages = listOf("com.akfgfragments")
    }
}