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
    implementation("com.github.tomakehurst:wiremock:2.27.2")

    implementation(kotlin("stdlib-jdk8"))
}

configurations {
    "implementation" {
        resolutionStrategy.failOnVersionConflict()
    }
}
tasks.withType<org.gradle.jvm.tasks.Jar>() {
    exclude("META-INF/BC1024KE.RSA", "META-INF/BC1024KE.SF", "META-INF/BC1024KE.DSA")
    exclude("META-INF/BC2048KE.RSA", "META-INF/BC2048KE.SF", "META-INF/BC2048KE.DSA")
}
tasks.test {
    useJUnitPlatform()
}