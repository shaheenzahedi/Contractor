group = "me.shaheen"
version = "1.0-SNAPSHOT"
plugins {
    kotlin("jvm") version "1.5.10"
    application
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.github.tomakehurst:wiremock-jre8:2.30.1")
    implementation("org.slf4j:slf4j-log4j12:2.0.0-alpha4")
    implementation(kotlin("stdlib-jdk8"))
}

configurations {
    "implementation" {
        resolutionStrategy.failOnVersionConflict()
    }
}

tasks.test {
    useJUnitPlatform()
}