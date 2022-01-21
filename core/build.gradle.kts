import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
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
    implementation ("org.codehaus.groovy:groovy-json:3.0.9")
    implementation ("org.springframework.cloud:spring-cloud-contract-spec:3.1.0")
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
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
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}