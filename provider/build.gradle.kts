group = "me.shaheen"
version = "1.0-SNAPSHOT"
plugins {
    kotlin("jvm") version "1.5.10"
    application
}

val koinVersion: String = "3.0.1"
allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("com.squareup:kotlinpoet:1.8.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.3")

    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-core-ext:$koinVersion")
    testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))
}

configurations {
    "implementation" {
        resolutionStrategy.failOnVersionConflict()
    }
}
tasks.test {
    useJUnitPlatform()
}