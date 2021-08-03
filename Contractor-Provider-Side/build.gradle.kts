import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

val koinVersion: String = "3.0.1"
repositories {
    mavenCentral()
}
//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}
//val compileTestKotlin: KotlinCompile by tasks
//compileTestKotlin.kotlinOptions {
//    jvmTarget = "1.8"
//}
dependencies {
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("com.squareup:kotlinpoet:1.8.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.3")

    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-core-ext:$koinVersion")
//    testImplementation("org.koin:koin-test:$koinVersion")
//    testImplementation("org.koin:koin-test-junit4:$koinVersion")
    testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
    implementation(kotlin("stdlib-jdk8"))
}

configurations {
    "implementation" {
        resolutionStrategy.failOnVersionConflict()
    }
}

group = "me.shaheen"
version = "1.0-SNAPSHOT"


tasks.test {
    useJUnitPlatform()
}
//
//tasks.withType<KotlinCompile>() {
//    kotlinOptions.jvmTarget = "1.8"
//}
//
//application {
//    mainClass.set("MainKt")
//}
//tasks.withType<Jar> {
//    manifest {
//        attributes["Main-Class"] = "MainKt"
//    }
//}
//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict")
//        jvmTarget = "1.8"
//    }
//
//    tasks.withType<Test> {
//        useJUnitPlatform()
//    }
//}
