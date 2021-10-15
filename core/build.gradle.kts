import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.shaheen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.3")
    implementation("org.json:json:20210307")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    implementation("com.natpryce:snodge:3.7.0.0")
    implementation("org.glassfish:javax.json:1.1")
    implementation("com.github.jkcclemens:khttp:0.1.0")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.0-M1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}