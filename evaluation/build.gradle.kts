group = "me.shaheen"
version = "1.0-SNAPSHOT"
plugins {
    kotlin("jvm")
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
