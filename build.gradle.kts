import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
version = "0.0.6"

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

//ktor {
//    fatJar {
//        archiveFileName.set("akfgfragments-data-server.jar")
//    }
//}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_22)
}

application {
    mainClass.set("com.akfgfragments.ApplicationKt")
}

graalvmNative {
    toolchainDetection.set(false)
    binaries {
        named("main") {
            verbose.set(true)

            buildArgs.add("--initialize-at-build-time=io.ktor,kotlin,ch.qos.logback,org.slf4j,kotlinx")
//            buildArgs.add("--trace-class-initialization=kotlinx.io.SegmentPool,kotlinx.io.files.FileSystemJvmKt,kotlinx.io.files.PathsJvmKt,kotlinx.io.bytestring.ByteString")
            buildArgs.add("-H:ReflectionConfigurationFiles=../../../src/main/resources/META-INF/native-compile/reflection.json")

            // options to configure the main binary
            imageName.set("akfgfragments-data-graphql-server-native-v${version}")
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