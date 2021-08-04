
plugins {
    kotlin("jvm") version "1.5.10"
    application
}

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
    implementation("com.github.tomakehurst:wiremock-jre8:2.29.1")
//    implementation("org.slf4j:slf4j-simple:1.6.1")

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
