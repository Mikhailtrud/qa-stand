plugins {
    java
}

group = "io.github.<your-github>"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // JUnit
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.launcher)

    // REST Assured
    testImplementation(libs.rest.assured)

    // Jackson
    testImplementation(libs.jackson.databind)

    // AssertJ
    testImplementation(libs.assertj)

    // Datafaker
    testImplementation(libs.datafaker)

    // Lombok
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}