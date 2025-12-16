import org.jetbrains.kotlin.gradle.tasks.UsesKotlinJavaToolchain

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
version = "0.1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.graphql.kotlin)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.websockets)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.statuspages)
    implementation(libs.ktor.server.config.yaml)
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

project.tasks.withType<UsesKotlinJavaToolchain>().configureEach {
    kotlinJavaToolchain.toolchain.use(project.extensions.getByType<JavaToolchainService>().launcherFor {
        languageVersion.set(JavaLanguageVersion.of(25))
    })
}

application {
    mainClass.set("com.akfgfragments.ApplicationKt")
}

graalvmNative {
    toolchainDetection.set(false)
    binaries {
        named("main") {
            verbose.set(true)

            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin,ch.qos.logback,org.slf4j,kotlinx,org.xml.sax.helpers.*")
            buildArgs.add("-H:ReflectionConfigurationFiles=../../../src/main/resources/META-INF/native-compile/reflection.json")

            // options to configure the main binary
            imageName.set("akfgfragments-data-graphql-server-v${version}")
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